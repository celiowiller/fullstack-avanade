package br.com.agro.domain.model;

import java.time.Instant;

public sealed interface SensorData permits Umidade, Temperatura, Gps{

}
