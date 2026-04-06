package com.agro.sensores.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizarSensorDTO(
	    @NotBlank
	    String nome
	) {}