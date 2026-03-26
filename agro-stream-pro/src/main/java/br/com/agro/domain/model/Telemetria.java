package br.com.agro.domain.model;

import java.time.Instant;

// record que pode conter qualquer tipo de sensor e metadados de localização
public record Telemetria(
		int id,
		Instant momento,
		Posicao posicao, // aqui, a Telemetria USA  o record Posicao e seus elementos
		SensorData sensor // aqui, a Telemetria USA qualquer sensor (que ser o Gps, por exemplo)
		) {

}
