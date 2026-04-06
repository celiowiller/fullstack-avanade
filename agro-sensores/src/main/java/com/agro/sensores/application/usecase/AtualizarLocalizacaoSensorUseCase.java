package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.model.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizarLocalizacaoSensorUseCase {

    private final SensorLocalizacaoRepository repository;

    @Transactional // PONTO CRUCIAL: Garante que: ou faz tudo ou não faz nada
    public void executar(String sensorId, String novaLocalizacao) {

        // 1. busca localização atual
        var atualOpt = repository.buscarAtivaPorSensor(sensorId);

        atualOpt.ifPresent(localAtual -> {
            localAtual.encerrar(LocalDateTime.now());
            repository.salvar(localAtual);
        });

        // 2. cria nova localização
        SensorLocalizacao nova = new SensorLocalizacao(
                null,
                sensorId,
                novaLocalizacao,
                LocalDateTime.now(),
                null
        );

        repository.salvar(nova);
    }
}