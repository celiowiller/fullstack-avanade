package com.agro.sensores.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public record LeituraRequestDTO(
    @NotBlank String sensorId,
    @NotNull Double valor,
    @NotNull 
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime dataHora
) {}