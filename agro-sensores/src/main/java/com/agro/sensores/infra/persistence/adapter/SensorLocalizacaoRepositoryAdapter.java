package com.agro.sensores.infra.persistence.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.infra.persistence.entity.SensorLocalizacaoEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorLocalizacaoRepository;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SensorLocalizacaoRepositoryAdapter implements SensorLocalizacaoRepository {

    private final JpaSensorLocalizacaoRepository jpa;
 // 1. Injetando o repositório que você acabou de mostrar
    private final JpaSensorRepository sensorJpa;

    @Override
    public SensorLocalizacao salvar(SensorLocalizacao localizacao) {
        return toDomain(jpa.save(toEntity(localizacao)));
    }

    @Override
    public Optional<SensorLocalizacao> buscarAtivaPorSensor(String sensorId) {
        // Usando o novo método que evita o erro de duplicidade
        return jpa.findFirstBySensor_IdAndDataFimIsNullOrderByDataInicioDesc(sensorId)
                  .map(this::toDomain);
    }

    public Optional<SensorLocalizacao> buscarPorSensorEData(String sensorId, LocalDateTime data) {
        return jpa.buscarPorSensorEData(sensorId, data)
                  .map(this::toDomain);
    }
    
 // Adicionando esta implementação no seu Adapter
    @Override
 // No SensorLocalizacaoRepositoryAdapter
     public List<SensorLocalizacao> buscarTodosPorSensor(String sensorId) { 
    	// Alinhado com a Interface
        return jpa.findAllBySensor_IdOrderByDataInicioDesc(sensorId)
                  .stream()
                  .map(this::toDomain)
                  .toList(); // Se der erro no toList, usamos .collect(Collectors.toList())
    }

    private SensorLocalizacao toDomain(SensorLocalizacaoEntity e) {
        return new SensorLocalizacao(
                e.getId(),
                e.getSensor().getId(),
                e.getLocalizacao(),
                e.getDataInicio(),
                e.getDataFim()
        );
    }

    private SensorLocalizacaoEntity toEntity(SensorLocalizacao d) {
        SensorLocalizacaoEntity entity = new SensorLocalizacaoEntity();
        entity.setId(d.getId());
        entity.setLocalizacao(d.getLocalizacao());
        entity.setDataInicio(d.getDataInicio());
        entity.setDataFim(d.getDataFim());

        // 2. Vinculando o Sensor usando o ID que vem do domínio
        if (d.getSensorId() != null) {
            // getReferenceById evita um SELECT desnecessário
            entity.setSensor(sensorJpa.getReferenceById(d.getSensorId()));
        }

        return entity;
    }
}