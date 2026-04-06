package com.agro.sensores.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.api.dto.SensorComLeiturasDTO;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarSensoresComLeiturasUseCase {

    private final SensorRepository sensorRepository;
    private final LeituraRepository leituraRepository;

    public List<SensorComLeiturasDTO> executar() {

        return sensorRepository.buscarTodos().stream()
            .map(sensor -> {
                var leituras = leituraRepository.buscarPorSensor(sensor.getId());

                return new SensorComLeiturasDTO(
                    sensor.getId(),
                    sensor.getNome(),
                    sensor.getLocalizacao(),
                    leituras
                );
            })
            .toList();
    }
}
