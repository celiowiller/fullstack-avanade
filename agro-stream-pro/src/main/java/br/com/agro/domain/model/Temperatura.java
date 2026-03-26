package br.com.agro.domain.model;

// record que representa a leitura de temperatura do sensor
import java.time.Instant;

public record Temperatura(
		int id,
		Instant tempo,
		double celsius,
		String escala
		) implements SensorData{

}
