package br.com.agro.domain.model;
import java.time.Instant;
// um record é uma estrutura de codigo com uma finalidade especial: atuar com um 
// "transportador de dados  - imutaveis"

// record que representa a localização geográfica do sensor
public record Gps(
		int id,           // Componente: o campo id se torna um elemento final 
		Instant tempo,    // Componente: define o momento temporal da leitura do dado
		double latitude,  // Componente: dado de precisão geografica
		double longitude  // Componente: dado de precisão geografica
		//Posicao posicao
		
		) implements SensorData{} // ao implementar esta interface, fechamos o "ciclo";
// aqui, estamos, com o record, aderindo ao "contrato" que será estabelecido entre este 
// record e a interface SensorData

// 1. public record Gps(): declaração do record
// 
