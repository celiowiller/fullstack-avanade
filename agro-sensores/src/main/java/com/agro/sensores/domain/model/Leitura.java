package com.agro.sensores.domain.model;

import java.time.LocalDateTime;

import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Leitura {
	private Long id;
	//private String sensorId;
	private Sensor sensor;
	private Double valor;
	private LocalDateTime dataHora;
	
	public Leitura(
			Long id,
			//String sensorId,
			// Sensor sensorId,
			Sensor sensor,
			Double valor,
			LocalDateTime dataHora
			) {
		// validações
		if(sensor == null) {
			throw new ValidacaoException("SensorId é obrigatorio!");
		}
		
		if(valor == null) {
			throw new ValidacaoException("Valor de leitura é obrigatorio!");
		}
		
		if(dataHora == null) {
			throw new ValidacaoException("Data/hora é obrigatorio!");
		}
		
		 this.id = id;
		 this.sensor = sensor;
		 this.valor = valor;
		 this.dataHora = dataHora;
	}
	
	//comportamentos do dominio
	public boolean isRecente() {
		return dataHora.isAfter(LocalDateTime.now().minusHours(1));
	}
	
	public boolean isValorValido(double min, double max) {
		return valor >= min && valor <= max;
	}
}
