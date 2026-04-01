package com.agro.sensores.domain.repository;

import java.util.List;

import com.agro.sensores.domain.model.Leitura;

public interface LeituraRepository {
	Leitura salvar(Leitura leitura);
	List<Leitura> buscarPorSensor(String sensorId);
}
