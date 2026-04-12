package com.agro.sensores.api.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SensorComLeiturasDTO(
	    String id,
	    String nome,
	    
	    @JsonProperty("localizacaoAtual") // Força o nome que o Angular está esperando
	    String localizacao,
	    
	    List<com.agro.sensores.domain.model.Leitura> leituras,
	    
	    // Dica: Adicionar o histórico aqui para o Dashboard detalhado funcionar
	    List<com.agro.sensores.domain.model.SensorLocalizacao> historico
	) {}