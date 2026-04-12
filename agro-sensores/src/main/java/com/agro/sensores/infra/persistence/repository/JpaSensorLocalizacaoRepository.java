package com.agro.sensores.infra.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agro.sensores.infra.persistence.entity.SensorLocalizacaoEntity;

public interface JpaSensorLocalizacaoRepository extends JpaRepository<SensorLocalizacaoEntity, String> {

    Optional<SensorLocalizacaoEntity> findFirstBySensor_IdAndDataFimIsNullOrderByDataInicioDesc(String sensorId);

    @Query("""
        SELECT s FROM SensorLocalizacaoEntity s
        WHERE s.sensor.id = :sensorId
        AND s.dataInicio <= :data
        AND (s.dataFim IS NULL OR s.dataFim >= :data)
        ORDER BY s.dataInicio DESC
    """)
    Optional<SensorLocalizacaoEntity> buscarPorSensorEData(
        @Param("sensorId") String sensorId,
        @Param("data") LocalDateTime data
    );

    List<SensorLocalizacaoEntity> findAllBySensor_IdOrderByDataInicioDesc(String sensorId);
}