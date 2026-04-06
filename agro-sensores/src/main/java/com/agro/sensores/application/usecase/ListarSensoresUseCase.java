package com.agro.sensores.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.agro.sensores.api.dto.response.SensorResponseDTO;
import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarSensoresUseCase {

    private final SensorRepository repository;
    private final SensorLocalizacaoRepository localizacaoRepository;
    private final LeituraRepository leituraRepository;

    public List<SensorResponseDTO> executar() {
        return repository.buscarTodos().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }
    
    public SensorResponseDTO buscarPorId(String id) {
        var sensor = repository.buscarPorId(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado"));

        return mapToResponse(sensor);
    }

    private SensorResponseDTO mapToResponse(Sensor sensor) {
        // 1. Busca os dados reais de histórico e telemetria
        var historicoDominio = localizacaoRepository.buscarTodosPorSensor(sensor.getId());
        var leiturasDominio = leituraRepository.buscarPorSensor(sensor.getId());

        // 2. Mapeia a lista de Histórico para o DTO
        List<SensorResponseDTO.LocalizacaoResponseDTO> historicoDTO = historicoDominio.stream()
            .map(h -> new SensorResponseDTO.LocalizacaoResponseDTO(
                h.getLocalizacao(), 
                h.getDataInicio(), 
                h.getDataFim()))
            .collect(Collectors.toList());

        // 3. Mapeia a lista de Telemetria para o DTO
        List<SensorResponseDTO.TelemetriaResponseDTO> leiturasDTO = leiturasDominio.stream()
            .map(l -> new SensorResponseDTO.TelemetriaResponseDTO(
                l.getValor(), 
                l.getDataHora()))
            .collect(Collectors.toList());

        // 4. Retorna o DTO Completo
        return new SensorResponseDTO(
            sensor.getId(),
            sensor.getNome(),
            sensor.getLocalizacao(), 
            sensor.getTipo().name(),
            sensor.isAtivo(),
            historicoDTO,
            leiturasDTO
        );
    }
    
    public void deletar(String id) {
        repository.deletar(id);
    }
}