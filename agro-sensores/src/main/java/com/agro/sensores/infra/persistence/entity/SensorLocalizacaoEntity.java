package com.agro.sensores.infra.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sensor_localizacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorLocalizacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private SensorEntity sensor;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;
}