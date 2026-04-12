package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.domain.strategy.ValidadorSensorStrategy;
import com.agro.sensores.infra.persistence.entity.SensorEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrarLeituraUseCase {

    private final SensorRepository sensorRepository;
    private final LeituraRepository leituraRepository;
    private final SensorLocalizacaoRepository localizacaoRepository;
    private final List<ValidadorSensorStrategy> validadores;

    public void executar(String sensorId, Double valor, LocalDateTime dataHora) {

        var sensor = sensorRepository.buscarPorId(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        var localizacaoHistorica = localizacaoRepository
                .buscarPorSensorEData(sensorId, dataHora)
                .orElseGet(() ->
                    localizacaoRepository.buscarAtivaPorSensor(sensorId)
                        .orElseThrow(() -> new RuntimeException("Sensor sem localização"))
                );

        Leitura leitura = new Leitura(
                null,
                sensor,
                valor,
                dataHora,
                localizacaoHistorica.getLocalizacao()
        );

        // ✔ valida primeiro
        validadores.stream()
                .filter(v -> v.suporta(sensor.getTipo()))
                .forEach(v -> v.validar(leitura));

        // ✔ salva UMA vez só
        leituraRepository.salvar(leitura);
    }
}