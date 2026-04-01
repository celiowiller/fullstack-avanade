package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.Sensor;

public interface SensorRepository {
	Sensor salvar(Sensor sensor);
	Optional<Sensor> buscarPorId(String id);
	List<Sensor> buscarTodos();
	void deletar(String id);
}
