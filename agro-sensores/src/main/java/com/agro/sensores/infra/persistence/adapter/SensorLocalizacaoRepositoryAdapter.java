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
    private final JpaSensorRepository sensorJpa;

    @Override
    public SensorLocalizacao salvar(SensorLocalizacao d) {
        return toDomain(jpa.save(toEntity(d)));
    }

    @Override
    public Optional<SensorLocalizacao> buscarAtivaPorSensor(String sensorId) {
        return jpa.findFirstBySensor_IdAndDataFimIsNullOrderByDataInicioDesc(sensorId)
                .map(this::toDomain);
    }

    @Override
    public Optional<SensorLocalizacao> buscarPorSensorEData(String sensorId, LocalDateTime data) {
        return jpa.buscarPorSensorEData(sensorId, data)
                .map(this::toDomain);
    }

    @Override
    public List<SensorLocalizacao> buscarTodosPorSensor(String sensorId) {
        return jpa.findAllBySensor_IdOrderByDataInicioDesc(sensorId)
                .stream()
                .map(this::toDomain)
                .toList();
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

        SensorLocalizacaoEntity e = new SensorLocalizacaoEntity();

        e.setId(d.getId());
        e.setLocalizacao(d.getLocalizacao());
        e.setDataInicio(d.getDataInicio());
        e.setDataFim(d.getDataFim());

        e.setSensor(
                sensorJpa.getReferenceById(d.getSensorId())
        );

        return e;
    }
}