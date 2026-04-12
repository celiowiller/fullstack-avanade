package com.agro.sensores.application.usecase;

import java.util.List;
import org.springframework.stereotype.Service;
import com.agro.sensores.api.dto.SensorComLeiturasDTO;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository; // Adicionado
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarSensoresComLeiturasUseCase {

    private final SensorRepository sensorRepository;
    private final LeituraRepository leituraRepository;
    private final SensorLocalizacaoRepository localizacaoRepository; // Adicionado

    public List<SensorComLeiturasDTO> executar() {
        return sensorRepository.buscarTodos().stream()
            .map(sensor -> {
                // 1. Busca as leituras
                var leituras = leituraRepository.buscarPorSensor(sensor.getId());
                
                // 2. AJUSTE: Chama o novo nome definido na interface
                // O Java agora reconhece como List<SensorLocalizacao> e não mais como Object
                var historico = localizacaoRepository.buscarTodosPorSensor(sensor.getId());

                // 3. Montagem do DTO (Agora com os 5 parâmetros tipados corretamente)
                return new SensorComLeiturasDTO(
                    sensor.getId(),
                    sensor.getNome(),
                    sensor.getLocalizacao(), 
                    leituras,
                    historico 
                );
            })
            .toList();
    }
}