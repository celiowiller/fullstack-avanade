package com.agro.sensores.infra.persistence.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.agro.sensores.infra.persistence.entity.LeituraEntity;

public interface JpaLeituraRepository extends JpaRepository<LeituraEntity, Long>{
	// buscar leituras por sensor
	List<LeituraEntity>  findAllBySensor_id(String sensorId);
	//Optional<Leitura> buscarUltimaPorSensor(String sensorId);
	
	
}
