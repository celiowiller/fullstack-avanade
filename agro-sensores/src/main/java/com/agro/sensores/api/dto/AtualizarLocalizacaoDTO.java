package com.agro.sensores.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizarLocalizacaoDTO(
	    @NotBlank
	    String localizacao
	) {}
