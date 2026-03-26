package br.com.agro.domain.model; // localização do dominio

import java.time.Instant;

public sealed interface SensorData permits Umidade, Temperatura, Gps{ 
	// acima, temos a interface sealed - comando que "encerra" qualquer tipo de mecanismo
	// de herança ou implementação desta interface
	// permits: declaração, explicita, de permissão de implementação da interface e seus membros
	int id(); // "Contrato": todo sensor deve prover um elemento de identificação
	Instant tempo(); // "Contrato": todo sensor deve dizer em que momento leu o dado
}

// agora, o "ambiente de execução" deve saber, exatamente, quais sensores existem.