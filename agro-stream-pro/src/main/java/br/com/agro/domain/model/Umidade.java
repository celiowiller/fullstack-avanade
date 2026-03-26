package br.com.agro.domain.model;

// recorde que representa a leitura de humidade do sensor
import java.time.Instant;

public record Umidade(
		int id,
		Instant tempo,
		double percentual
		) implements SensorData {}
