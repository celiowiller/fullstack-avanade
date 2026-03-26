package br.com.agro.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.agro.domain.model.Gps;
import br.com.agro.domain.model.SensorData;
import br.com.agro.domain.model.Temperatura;
import br.com.agro.domain.model.Umidade;

/*
 * esta camada é responsavel por processar o fluxo de dados de sensores.
 * aqui,  vamos aplicar o conceito/tecnicas de Data-Oriented-Programming
 * */
public class ProcessadorTelemetria {
	// processar uma lista de telemetrias utilizando o recurso Stream
	// 1º passo
	public void processar(List<Optional<SensorData>> sensores) { // parallelStream()
		sensores.stream() // transformando nossa lista em um fluxo de dados 
					.flatMap(Optional::stream) // aqui, o método flatMap nos auxila com dois
					// contexto de muita importancia: 1º ele filtra tudo aquilo qeu for, eventualmente
					// vazio/empty; 2º também "desempacota/desembrulha" aquilo que tiver valor - que não
					// seja vazio/empty - a partir de uma unica linha de instrução
					// :: -> Method Reference
					.map(this::formatarLeitura)// o método .map() nos auxilia na formatação que queremos
					// aplicar aos dados - a partir do momento em que o método os "mapeia"
					.forEach(System.out::println); // uso do método forEach() para que seja 
										// possivel, logo após o mapeamento, imprimir os dados na tela
	}
	
	/*
	 * agora, vamos definir o "coração" do processo de formatação/refatoração: Pattern Matching - 
	 * usando switch
	 * 
	 * é a possibilidade do nosso compilador testar/observar se um objeto possui uma determinada forma
	 * 
	 * */
	private String formatarLeitura(SensorData dado) {
		// aqui, vamos usar o switch expression - ao utiliza-lo, teremos a possibilidadde retornar
		// diretamente a variavel de 'saida'
		String saida = switch (dado) {
			case Umidade u -> String.format("[ID %d] Umidade: %.2f%%", u.id(), u.percentual() * 100);
			case Temperatura t -> String.format("[ID %d] Temperatura: %.1f%s", t.id(), t.celsius(), t.escala());
			case Gps g -> String.format("[ID %d] Coordenadas: Lat %.4f, Long %.4f", g.id(), g.latitude(), g.longitude());
		};
		
		// agora, a variavel saida existe e, então, poderemos concatnar com a Thread das operações
		return String.format("%s | Executado por: %s", saida, Thread.currentThread().getName());
	}
	
	
}
