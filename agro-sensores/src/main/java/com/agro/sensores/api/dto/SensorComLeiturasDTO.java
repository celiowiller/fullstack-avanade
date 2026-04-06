package com.agro.sensores.api.dto;

import java.util.List;

public record SensorComLeiturasDTO(
	    String id,
	    String nome,
	    String localizacao,
	    List<com.agro.sensores.domain.model.Leitura> leituras
	) {}
