package com.agro.sensores.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;

// interface JPA para acesso ao DB
public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, String> {
	// busca por login
	Optional<UsuarioEntity> findByLogin(String login);
	
	// Optional<T>: estamos tentando evitar o famigerado NullPointerException - porque estamos 
	// deixando
	// claro que um valor PODE ESTAR AUSENTE
}
