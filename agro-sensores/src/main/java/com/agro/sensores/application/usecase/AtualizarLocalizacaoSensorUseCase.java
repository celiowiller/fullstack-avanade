package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.model.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizarLocalizacaoSensorUseCase {

    private final SensorLocalizacaoRepository localizacaoRepository;
    private final SensorRepository sensorRepository;

    @Transactional
    public void executar(String sensorId, String novaLocalizacao) {

        var sensor = sensorRepository.buscarPorId(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        LocalDateTime agora = LocalDateTime.now();

        localizacaoRepository.buscarAtivaPorSensor(sensorId)
                .ifPresent(antigo -> {
                    antigo.encerrar(agora);
                    localizacaoRepository.salvar(antigo);
                });

        SensorLocalizacao nova = new SensorLocalizacao(
                null,
                sensorId,
                novaLocalizacao,
                agora,
                null
        );

        localizacaoRepository.salvar(nova);

        sensor.alterarLocalizacao(novaLocalizacao);
        sensorRepository.salvar(sensor);
    }
}