package br.com.agro;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;

import br.com.agro.domain.model.Posicao;
import br.com.agro.domain.model.Telemetria;
import br.com.agro.domain.service.ProcessadorTelemetria;
import br.com.agro.infra.repository.TelemetriaRepository;
import br.com.agro.infra.simulation.GeradorSensores;

/*
 este é o entrypoint da aplicação
 seu apelido é: "Orquestrador principal" 
 */
public class MainClass {

	public static void main(String[] args) {
		// inicialização do componentes de Infra e Service
		var repository = new TelemetriaRepository();
		var processamento = new ProcessadorTelemetria();
		
		// geração da massa de dados dos sensores
		int totalSensores = 1000; // este será o valor indicado para 
		// a quantidade de threads que serão estabelecidas
		var obsTelemetria = GeradorSensores.gerarMassaDeDados(totalSensores);
		
		System.out.println("---- Inciando Processamento ----");
		
		Instant inicio = Instant.now();
		
		// Execução massiva com virtual threads
		/*
		 este é um try-with-resources: aqui, não é apenas uma observação/tratamento de
		 potenciais exceções;
		 */
		try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
			obsTelemetria.forEach(
						optSensor -> {
							// 1. processamento do pattern matching/formatação
							executor.submit(() -> {
								processamento.processar(List.of(optSensor));
							// 2. contexto de persistencia: se o sensor existir/online ele é persistido
							optSensor.ifPresent(sensor -> {
								 var t = new Telemetria(sensor.id(), sensor.tempo(), 
										 new Posicao(0,0), sensor);
								 repository.salvar(t);
								});
							});
						}
					);
		} // fechamento do bloco try
		catch(Exception e) {
			System.err.println("ERRO MUITO CRÍTICO NO PROCESSAMENTO MASSIVO: "+ e.getMessage());
		}
		// exibição do relatorio final de performance
		Instant fim = Instant.now();
		
		long tempoTotal = Duration.between(inicio, fim).toMillis();
		
		System.out.println("\n"+ "=".repeat(50));
		
		System.out.printf("PROCESSAMENTO CONCLUIDO EM: %dms%n", tempoTotal);
		
		System.out.printf("TOTAL DE SENSORES PROCESSADOS: %d%n", totalSensores);
		
		System.out.printf("REGISTROS SALVOS NO REPOSITORY: %d%n ", repository.contarRegistros());
		
		System.out.println("=".repeat(50));
	}

}
