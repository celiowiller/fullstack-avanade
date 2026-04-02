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
//import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class LeituraRepositoryAdapter implements LeituraRepository {
	private final JpaLeituraRepository jpa;
	
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
		// precisamos converter o Sensor que no esquema
		/*return new Leitura(
					entity.getId(),
					entity.getSensor().getId(),
					entity.getValor(),
					entity.getDataHora()
					
				);*/
		
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
					entity.getDataHora()
				);
	}
	
	// Mapper: Domain -> Entity
	private LeituraEntity toEntity(Leitura leitura) {
		
		// neste passo, vamos criar uma "casca" de SensorEntity apenas com o ID 
		// para que o JPA possa saber a qual sensor o id pertence
		SensorEntity sensorEntity = new SensorEntity();
		sensorEntity.setId(leitura.getSensor().getId()); // 
		
		// retorna a Entity com os campos que, realmente, estão disponiveis no ocntexto
		return new LeituraEntity(
					/*leitura.getId(),
					//leitura.getSensorId(),
					leitura,
					leitura.getValor(),
					leitura.getDataHora(),
					leitura.isRecente()
					//leitura.isValorValido()*/
				leitura.getId(),
				sensorEntity, // <---- aqui esta o objeto e os elementos que, à ele, pertencem
				leitura.getValor(),
				leitura.getDataHora()
				);
	}
	
}

// ***** precisamos observar e relacionar como os dados "serão passados" para a estrutura da
//Entity - LeituraEntity????? 

// TAMBEM - A PARTIR DA PROVOCAÇÃO DO ELIZEU - PASSAR ALGUNS CAMPOS - COMO POR EXEMPLO... 
// localização 
