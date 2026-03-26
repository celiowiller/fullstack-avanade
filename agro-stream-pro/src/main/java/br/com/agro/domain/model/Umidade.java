package br.com.agro.domain.model;

// record que representa a leitura de humidade do sensor
import java.time.Instant;

public record Umidade(
		int id,
		Instant tempo,
		double percentual
		) implements SensorData {}
