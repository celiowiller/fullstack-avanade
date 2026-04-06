package com.agro.sensores.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

// DTO para envio de leitura
public record LeituraRequestDTO(

        @NotBlank String sensorId,
        @NotNull Double valor,
        @NotNull LocalDateTime dataHora
) {}
