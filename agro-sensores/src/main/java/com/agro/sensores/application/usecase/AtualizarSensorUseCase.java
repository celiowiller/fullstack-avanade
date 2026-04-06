package com.agro.sensores.application.usecase;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizarSensorUseCase {

    private final SensorRepository repository;

    public void executar(String id, String nome) {

        Sensor sensor = repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

        // cria novo objeto (imutabilidade - ideal no domínio)
        Sensor atualizado = new Sensor(
                sensor.getId(),
                nome,
                sensor.getLocalizacao(),
                sensor.getTipo(),
                sensor.isAtivo()
        );

        repository.salvar(atualizado);
    }
}
