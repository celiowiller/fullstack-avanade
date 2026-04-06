package com.agro.sensores.infra.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agro.sensores.infra.persistence.entity.SensorLocalizacaoEntity;

public interface JpaSensorLocalizacaoRepository 
extends JpaRepository<SensorLocalizacaoEntity, String> {
		
	// 1. Busca a localização ATUAL (Atende o Use Case de Atualização)
	//Optional<SensorLocalizacaoEntity> findBySensor_IdAndDataFimIsNull(String sensorId);
	
	// Alterado para findFirst...OrderBy... para evitar o erro NonUniqueResultException
	// Mudando para findFirst, o Spring ignora as duplicatas e pega apenas a mais recente
    Optional<SensorLocalizacaoEntity> findFirstBySensor_IdAndDataFimIsNullOrderByDataInicioDesc(String sensorId);
		
    // 2. Busca TODO o histórico (Atende seu Requisito 3)
	// Ordenado do mais recente para o mais antigo
	List<SensorLocalizacaoEntity> findAllBySensor_IdOrderByDataInicioDesc(String sensorId);
		
	// 3. Localização em um momento específico (JPQLQuery Customizada)
	@Query("""
		SELECT l FROM SensorLocalizacaoEntity l
		WHERE l.sensor.id = :sensorId
		AND l.dataInicio <= :data
		AND (l.dataFim IS NULL OR l.dataFim >= :data)
		""")
		Optional<SensorLocalizacaoEntity> buscarPorSensorEData(
		@Param("sensorId") String sensorId, 
		@Param("data") LocalDateTime data
		);
}
