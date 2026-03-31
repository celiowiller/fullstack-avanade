package com.agro.sensores.domain.strategy;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.model.Leitura;

// interface strategy para validação de sensores
public interface ValidadorSensorStrategy {
	// verificar se o validador suporta o tipo de sensor
	boolean suporta(TipoSensor tipo);
	
	// executar a validação da leitura
	void validar(Leitura leitura);
}
