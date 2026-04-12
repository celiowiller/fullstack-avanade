package com.agro.sensores.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class SensorLocalizacao {

    private String id;
    private String sensorId;
    private String localizacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public SensorLocalizacao(
            String id,
            String sensorId,
            String localizacao,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {

        if (sensorId == null) {
            throw new RuntimeException("Sensor é obrigatório");
        }

        if (localizacao == null || localizacao.isBlank()) {
            throw new RuntimeException("Localização é obrigatória");
        }

        if (dataInicio == null) {
            throw new RuntimeException("Data início obrigatória");
        }

        this.id = id;
        this.sensorId = sensorId;
        this.localizacao = localizacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

 // MÉTODO PARA ENCERRAR A LOCALIZAÇÃO
    public void encerrar(LocalDateTime dataEncerramento) {
        if (this.dataFim != null) {
            throw new RuntimeException("Esta localização já foi encerrada!");
        }
        this.dataFim = dataEncerramento;
    }

    public boolean isAtivo() {
        return this.dataFim == null;
    }
}