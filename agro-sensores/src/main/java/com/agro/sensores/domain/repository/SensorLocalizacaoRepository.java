package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.model.SensorLocalizacao;

public interface SensorLocalizacaoRepository {

	SensorLocalizacao salvar(SensorLocalizacao localizacao);
    Optional<SensorLocalizacao> buscarAtivaPorSensor(String sensorId);
     
    // Alterando o nome aqui para estar em conformidade com o Use Case:
    List<SensorLocalizacao> buscarTodosPorSensor(String sensorId);
}