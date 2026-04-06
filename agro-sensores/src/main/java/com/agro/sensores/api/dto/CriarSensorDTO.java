package com.agro.sensores.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarSensorDTO(

    @NotBlank
    String nome,

    @NotBlank
    String localizacao,

    @NotNull
    com.agro.sensores.domain.enums.TipoSensor tipo
) {}
