package com.agro.sensores.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Sensor;
import com.agro.sensores.domain.model.Usuario;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SensorRepositoryAdapter implements SensorRepository {
	private final JpaSensorRepository jpa;
	
	/*public Sensor salvar(Sensor sensor){
		SensorEntity sensor = toEntity(sensor);
	}*/
	// salvar sensor
	public Sensor salvar(Sensor sensor) {
		return toDomain(jpa.save(toEntity(sensor)));
	}
	
	public Optional<Sensor> buscarPorId(String id){
		return jpa.findById(id).map(this::toDomain);
	}
	
	public List<Sensor> buscarTodos(){
		return jpa.findAll().stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}
	
	public Optional<Sensor> buscaPorId(String id){
		return jpa.findById(id).map(this::toDomain);
	}
	
	public void deletar(String id) {
		jpa.deleteById(id);
	}
	
	// processo/bloco que converte entidade <-> dominio 
		// são conhecidos como Mappers - este métodos atuam como se fossem "tradutores" 
		private Sensor toDomain(SensorEntity entity) {
			// expressão de retorno do método. É na expressão de retorno que a "conversão" se dá
			return new Sensor(
						entity.getId(),
						entity.getNome(),
						entity.getLocalizacao(),
						entity.getTipo(),
						entity.isAtivo()
					);
		}
		
		// "conversão" de domain/dominio em entity
		private SensorEntity toEntity(Sensor sensor) {
			return new SensorEntity(
						sensor.getId(),
						sensor.getNome(),
						sensor.getLocalizacao(),
						sensor.getTipo(),
						sensor.isAtivo()
					);
		}

		
	
}
