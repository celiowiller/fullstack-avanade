package com.agro.sensores.infra.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leituras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LeituraEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Relacionamento com sensor(muitos para um)
	@ManyToOne
	@JoinColumn(name = "sensor_id", nullable = false)
	//@com.fasterxml.jackson.annotation.JsonIgnore // <--- ADICIONE ISSO AQUI
	private SensorEntity sensor;
	
	// valor da leitura
	@Column(nullable = false)
	private Double valor;
	
	// data e hora
	@Column(nullable = false)
	private LocalDateTime dataHora;
	
	@Column(nullable = false)
	private String localizacao;	
		
}
