package br.com.agro.infra.repository;

import java.util.concurrent.ConcurrentLinkedQueue;

import br.com.agro.domain.model.Telemetria;

// Repository de Infraestrutura
// Simular uma persistencia de dados em memoria
public class TelemetriaRepository {
	// aqui, com a definição do objeto da classe ConcurrentLinkedQueue
	// temos uma fila de dados!!!! então, a partir desta fila podemos fazer uso de
	// varias Threads que, eventualmente, adicionem dados à base simulada, 
	// AO MESMO TEMPO sem risco de "quebra".
	private final ConcurrentLinkedQueue<Telemetria> dbSimulado = new ConcurrentLinkedQueue<>();
	
	/* este método recebe o record Telemetria -seja la o que estiver contido nele - o método
	 *  armazena!
	 * 
	 */
	public void salvar(Telemetria telemetria) {
		simularDelayHardware(); // aqui, temos o método que simula a "trajetoria" e tempo que 
		// o pacote de dados leva para sair do ponto A e chegar o ponto B e ser armazenado.
		
		dbSimulado.add(telemetria); // este é o método que armazena dados na base simulada
		
		System.out.printf("[REPOSITORY] %s salvo com sucesso no 'db' | Thread: %s%n ",
					telemetria.sensor() // obtem o sensor - seja ele qual for 
						.getClass() // tenta observar qual é o sensor obtido
						.getSimpleName(), // aqui, retornamos o nome "amigavel"  de uma classe
						Thread.currentThread() // obtendo a thread em execução/executada
						.isVirtual()? "Virtual" : "Plataforma"
							// acima, está a "certidão de nascimento" : estamos avaliando se nossa 
							// Thread é virtual ou não; se sim, temos um procedimento "leve" 
							// se não, temos um procedimento de plataforma - "pesado"
				);
	}
	
	public long contarRegistros() {
		return dbSimulado.size(); // aqui, estamos fazendo a contagem dos registros armazenados
	}
	
	private void simularDelayHardware() {
		try {
			Thread.sleep(10); // representa 10 milisegundos - aqui, estamos simulando 
			// o tempo que levaria para aramzenarmos o conjunto de dados
		}catch(InterruptedException e) { // aqui, estamos capturando o sinal enquanto a thread "dorme"
			Thread.currentThread().interrupt(); // ao operar com a "invocação" interrupt()
			// estamos limpando o status de interrupção - caso ela [interrupção] ocorra.
			// e.printStackTrace()
		}
	}
}
