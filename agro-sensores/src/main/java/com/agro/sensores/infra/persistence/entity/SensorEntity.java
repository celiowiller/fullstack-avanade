package com.agro.sensores.infra.persistence.entity;
// entidade que representa sensores cadastrados no sistema

import com.agro.sensores.domain.enums.TipoSensor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensores")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SensorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String nome;
	
	// localização fisica
	private String localizacao;
	
	// Tipo do sensor
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoSensor tipo;
	
	// Indicar se o sensor está ativo
	@Column(nullable = false)
	private boolean ativo;
}
