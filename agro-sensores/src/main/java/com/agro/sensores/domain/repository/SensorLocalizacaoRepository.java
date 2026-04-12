package com.agro.sensores.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.SensorLocalizacao;

public interface SensorLocalizacaoRepository {

    SensorLocalizacao salvar(SensorLocalizacao localizacao);

    Optional<SensorLocalizacao> buscarAtivaPorSensor(String sensorId);

    Optional<SensorLocalizacao> buscarPorSensorEData(String sensorId, LocalDateTime data);

    List<SensorLocalizacao> buscarTodosPorSensor(String sensorId);
}