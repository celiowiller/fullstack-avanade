package com.agro.sensores.infra.persistence.adapter;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SensorRepositoryAdapter implements SensorRepository {

    private final JpaSensorRepository jpa;
    

    @Override
    public Sensor salvar(Sensor sensor) {
        // 1️. Salva o sensor
        SensorEntity savedEntity = jpa.save(toEntity(sensor));
      
        // 2. Retorna sensor do domínio
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Sensor> buscarPorId(String id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Sensor> buscarTodos() {
        return jpa.findAll().stream()
                  .map(this::toDomain)
                  .collect(Collectors.toList());
    }

    @Override
    public void deletar(String id) {
        jpa.deleteById(id);
    }

    private Sensor toDomain(SensorEntity entity) {
        return new Sensor(
            entity.getId(),
            entity.getNome(),
            entity.getLocalizacao(),
            entity.getTipo(),
            entity.isAtivo()
        );
    }

    private SensorEntity toEntity(Sensor sensor) {
        return new SensorEntity(
            sensor.getId(),
            sensor.getNome(),
            sensor.getLocalizacao(),
            sensor.getTipo(),
            sensor.isAtivo(),
            null, // Campo 'historico' (novo)
            null  // Campo 'leituras'  (novo)
        );
    }
}