package com.agro.sensores.infra.persistence.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Leitura;
import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.infra.persistence.entity.LeituraEntity;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaLeituraRepository;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LeituraRepositoryAdapter implements LeituraRepository {
	private final JpaLeituraRepository jpa;
	private final JpaSensorRepository sensorRepository;
	
	public Leitura salvar(Leitura leitura) {
		return toDomain(jpa.save(toEntity(leitura)));
	}
	
	public List<Leitura> buscarPorSensor(String sensorId){
		return jpa.findAllBySensor_id(sensorId).stream()
				.map(this::toDomain)
				.collect(Collectors.toList());				
	}
	
	// Mapper: Entity -> Domain
	private Leitura toDomain(LeituraEntity entity) {
				
		// 1º. "traduzimos" o "inquilino" (Sensor)

		Sensor sensorDomain = new Sensor(
				entity.getSensor().getId(),
				entity.getSensor().getNome(),
				entity.getSensor().getLocalizacao(),
				entity.getSensor().getTipo(),
				entity.getSensor().isAtivo()
				);
		// 2º. na sequencia, "montaremos" a Leitura com o Sensor já "traduzido"/acesso
		return new Leitura(
					entity.getId(),
				    sensorDomain, // <---- aqui, passamos o objeto 
					entity.getValor(),
					entity.getDataHora(),
					entity.getLocalizacao() // <--- indicado para o tratamento e uso de UseCases
				);
	}
		
	private LeituraEntity toEntity(Leitura leitura) {

	    SensorEntity sensorEntity = sensorRepository
	        .findById(leitura.getSensor().getId())
	        .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

	    return new LeituraEntity(
	        leitura.getId(),
	        sensorEntity,
	        leitura.getValor(),
	        leitura.getDataHora(),
	        leitura.getLocalizacao() // <--- indicado para o tratamento e uso de UseCases
	    );
	}
	
}

// ***** precisamos observar e relacionar como os dados "serão passados" para a estrutura da
//Entity - LeituraEntity????? 

// TAMBEM - A PARTIR DA PROVOCAÇÃO DO ELIZEU - PASSAR ALGUNS CAMPOS - COMO POR EXEMPLO... 
// localização 
