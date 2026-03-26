package br.com.agro.infra.simulation;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import br.com.agro.domain.model.Gps;
import br.com.agro.domain.model.SensorData;
import br.com.agro.domain.model.Temperatura;
import br.com.agro.domain.model.Umidade;

/*
 o objetivo deste arquivo/classe é: gerar/fornecer os dados para 
 que o nosso sistema possa operar
 
 para este proposito não vamos trabalhar com uma lista/conjuto de dados
 estaticos e sim com um novo strema. Portanto, vamos construir ujm novo 
 pipeline 
 */
public class GeradorSensores {
	// static: ao usar este elemento estamos dizendo que: não precisamos instanciar a classe 
	// para acessar seus elementos descritos  -desde que sejam static
	private static final Random RANDOM = new Random(); // aqui, temos o objeto que gera
	// valores randomicos para a similação
	// o range/intervalo de valores é gerado entre 0.0 e 1.0
	
	/*
	 este método gera, pra nós, uma lista de "telemetrias/sensores" de forma aleatória,
	 incluindo "telemetrias/sensores" eventualmente vazios. O parametro quantidade define 
	 o numero de registros no sistema/aplicação
	 * */
	public static List<Optional<SensorData>> gerarMassaDeDados(int quantidade){
		return IntStream.range(0, quantidade) // O contador - formando por um Stream 
				//fazendo do método range(); tambem imutavel e inicia em zero e 
				// vai até o valor que for atribuido ao 2º parametro - quantidade
				.mapToObj(GeradorSensores::criarSensorAleatorio) // A transformação
				// 
				//.mapToObj(this::criarSensorAleatorio)
				.toList(); // A coleta... estamos coletando os dados e gerando a lista de elementos
		// imutaveis.
				// .collect(Collectors.toList())
	}
	
	private static Optional<SensorData> criarSensorAleatorio(int id){
		int tipo  = RANDOM.nextInt(4);
		Instant agora = Instant.now();
		return switch (tipo) {
			case 0 -> Optional.of(new Umidade(id, agora, RANDOM.nextDouble())); 
			// cria um record/sensor de Umidade com o valor entre 0% a 100%
			
			case 1 -> Optional.of(new Temperatura(id, agora, 20 + RANDOM.nextDouble() *15, 
					"C"));
			// cria um record/sensor de Temperatura 20 graus e 35 graus 1.0
			
			//case 2 -> Optional.of(new Gps(id, agora, -23.5 + RANDOM.nextDouble(),
					//-47.4 + RANDOM.nextDouble()));
			case 2 -> Optional.of(new Gps(id, agora, -23.5 + RANDOM.nextDouble(), 
					-47.4 + RANDOM.nextDouble()));
			
			default -> Optional.empty(); // simulando falha de lieutra ou um sensor offline
		};
		
	}
}
