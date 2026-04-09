package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.agro.sensores.api.dto.CriarSensorDTO;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.model.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastrarSensorUseCase {

    private final SensorRepository sensorRepository;
    private final SensorLocalizacaoRepository localizacaoRepository;

    @Transactional // a annotation @Transactional nos auxilia
    // em definir uma "transação" de banco de dados
    public Sensor executar(CriarSensorDTO dto) {
        // Passo 0: salva o sensor -
        // Criamos o objeto de domínio do Sensor SEM a localização na lista interna por enquanto
        Sensor novoSensor = new Sensor(
                null,
                dto.nome(),
                dto.localizacao(),
                dto.tipo(),
                true
        );

        // 1. Salva o sensor primeiro para obter o ID gerado
        Sensor sensorSalvo = sensorRepository.salvar(novoSensor);

        // 2. Cria a localização vinculada explicitamente
        SensorLocalizacao localizacao = new SensorLocalizacao(
                null,
                sensorSalvo.getId(), 
                dto.localizacao(),
                LocalDateTime.now(),
                null
        );
        
        // 3. Salva a localização. 
        // Se o seu SensorRepositoryAdapter não estiver salvando localizações 
        // automaticamente, este é o único lugar que deve ter o save.
        localizacaoRepository.salvar(localizacao);

        return sensorSalvo;
    }
}