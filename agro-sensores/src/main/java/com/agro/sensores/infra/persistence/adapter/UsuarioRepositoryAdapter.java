package com.agro.sensores.infra.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Usuario;
import com.agro.sensores.domain.repository.UsuarioRepository;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import lombok.RequiredArgsConstructor;

// o que é um adapter: seu objetivo é "transformar/converter\" o domain <-> entity
// este é um elemento FUNDAMENTAL DA ARQUITETURA HEXAGONAL
@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepository { // ESTE É NOSSO 
	// PERSISTENCE ADAPTER
	
	// este adapter irá conectar o dominio(domain) com o DB
	
	// repositorio jpa: definir o elemento de referencia
	// ESTE É O ELEMENTO DE REFERENCIA PARA A INJEÇÃO DE DEPENDENCIA!
	private final JpaUsuarioRepository jpa;
	
	// Buscar por ID
	public Optional<Usuario> buscarPorId(String id){
		return jpa.findById(id)
				//.map(this::toDomain);
	}
}
