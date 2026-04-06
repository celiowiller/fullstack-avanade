package com.agro.sensores.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.domain.strategy.ValidadorSensorStrategy;
import com.agro.sensores.infra.persistence.adapter.SensorLocalizacaoRepositoryAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrarLeituraUseCase {

    // INJETANDO AS INTERFACES DO DOMÍNIO (Portas), NÃO O JPA DIRETAMENTE
    private final SensorRepository sensorRepository; 
    private final LeituraRepository leituraRepository;
    private final List<ValidadorSensorStrategy> validadores;
    private final SensorLocalizacaoRepositoryAdapter localizacaoRepository;

    public void executar(String id, Double valor, java.time.LocalDateTime dataHora) {

        // 1. Busca o SENSOR (O seu Adapter já faz a conversão e devolve um objeto Sensor do domínio)
        Sensor sensor = sensorRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

        // 2. Agora o objeto 'sensor' é do tipo Sensor (Domain), exatamente o que a Leitura espera!
        //Leitura leitura = new Leitura(null, sensor, valor, dataHora);
        var sensorLocalizacao = localizacaoRepository
                .buscarPorSensorEData(id, dataHora)
                .orElseThrow(() -> new RuntimeException(
                    "Localização não encontrada para sensor " + id + " em " + dataHora
                ));

        Leitura leitura = new Leitura(
                null,
                sensor,
                valor,
                dataHora,
                sensorLocalizacao.getLocalizacao()
        );

        // 3. Executa as validações de Strategy usando o objeto de domínio
        validadores.stream()
                .filter(v -> v.suporta(sensor.getTipo()))
                .forEach(v -> v.validar(leitura));

        // 4. Salva a LEITURA (O seu LeituraRepositoryAdapter cuidará da conversão para Entity)
        leituraRepository.salvar(leitura);
    }
}