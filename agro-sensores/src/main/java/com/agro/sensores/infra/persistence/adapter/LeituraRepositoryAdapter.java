package com.agro.sensores.infra.persistence.adapter;

import java.util.List;

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
	private final JpaSensorRepository sensorJpa;

	@Override
	public Leitura salvar(Leitura leitura) {
		return toDomain(jpa.save(toEntity(leitura)));
	}

	@Override
	public List<Leitura> buscarPorSensor(String sensorId) {
		return jpa.findBySensor_IdOrderByDataHoraDesc(sensorId)
				.stream()
				.map(this::toDomain)
				.toList();
	}

	// =========================
	// ENTITY -> DOMAIN
	// =========================
	private Leitura toDomain(LeituraEntity e) {

        SensorEntity se = sensorJpa.findById(e.getSensor().getId())
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado no banco"));

        if (se.getNome() == null) {
            throw new RuntimeException("Sensor sem nome no banco: " + se.getId());
        }
        Sensor sensor = new Sensor(
                se.getId(),
                se.getNome(),
                se.getLocalizacao(),
                se.getTipo(),
                se.isAtivo()
        );

        return new Leitura(
                e.getId(),
                sensor,
                e.getValor(),
                e.getDataHora(),
                e.getLocalizacao()
        );
    }
	
	/*private Leitura toDomain(LeituraEntity e) {
	    // Cria referência leve do sensor (apenas com ID)
	    Sensor sensorRef = new Sensor(
	        e.getSensor().getId(),
	        null, null, null, false // preencha conforme seu construtor
	    );
	    
	    return new Leitura(
	        e.getId(),
	        sensorRef,
	        e.getValor(),
	        e.getDataHora(),
	        e.getLocalizacao()
	    );
	}*/

	// =========================
	// DOMAIN -> ENTITY
	// =========================
	/*private LeituraEntity toEntity(Leitura d) {

	    LeituraEntity e = new LeituraEntity();

	    e.setId(d.getId());
	    e.setValor(d.getValor());
	    e.setDataHora(d.getDataHora());
	    e.setLocalizacao(d.getLocalizacao());

	    if (d.getSensor() == null || d.getSensor().getId() == null) {
	        throw new RuntimeException("Sensor não pode ser nulo na Leitura");
	    }

	    SensorEntity sensorEntity = new SensorEntity();
	    sensorEntity.setId(d.getSensor().getId());

	    e.setSensor(sensorEntity);

	    return e;
	}*/
	private LeituraEntity toEntity(Leitura d) {

	    LeituraEntity e = new LeituraEntity();

	    e.setId(d.getId());
	    e.setValor(d.getValor());
	    e.setDataHora(d.getDataHora());
	    e.setLocalizacao(d.getLocalizacao());

	    if (d.getSensor() == null || d.getSensor().getId() == null) {
	        throw new RuntimeException("Sensor não pode ser nulo na Leitura");
	    }

	    SensorEntity sensorRef = new SensorEntity();
	    sensorRef.setId(d.getSensor().getId());

	    e.setSensor(sensorRef);

	    return e;
	}
}