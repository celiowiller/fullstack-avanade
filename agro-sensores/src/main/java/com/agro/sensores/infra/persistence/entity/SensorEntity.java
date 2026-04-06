package com.agro.sensores.infra.persistence.entity;
// entidade que representa sensores cadastrados no sistema

import java.util.List;

import com.agro.sensores.domain.enums.TipoSensor;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensores")
@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor // - esta annotation gera um construtor com todos os campos/fields
//da classe como argumentos
@EqualsAndHashCode(of = "id")
public class SensorEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String nome;
    
    private String localizacao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSensor tipo;
    
    @Column(nullable = false)
    private boolean ativo;

    // --- ADICIONANDO ESTES MAPEAMENTOS PARA PREVINIR POETENCIAL ERRO 500 ---

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorLocalizacaoEntity> historico;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeituraEntity> leituras; 
    // Nota: Verifique se o nome da sua classe de telemetria é TelemetriaEntity ou LeituraEntity
}
