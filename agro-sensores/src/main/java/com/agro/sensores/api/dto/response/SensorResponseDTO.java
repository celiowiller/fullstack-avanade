package com.agro.sensores.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record SensorResponseDTO(
    String id,
    String nome,
    String localizacaoAtual,
    String tipo,
    boolean ativo,
    List<LocalizacaoResponseDTO> historico,
    List<TelemetriaResponseDTO> leituras
) {
    // Records internos para o histórico e leituras
    public record LocalizacaoResponseDTO(
        String localizacao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
    ) {}

    public record TelemetriaResponseDTO(
        Double valor,
        LocalDateTime dataHora
    ) {}
}
