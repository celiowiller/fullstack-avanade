package com.agro.sensores.domain.repository;

import java.util.Optional;

import com.agro.sensores.domain.model.Usuario;

// interface de repositorio do dominio (não depende do JPA)
public interface UsuarioRepository {
	
	// Buscar usuario pelo seu ID
	Optional<Usuario> buscarPorId(String id);
	
	// buscar usuario por login
	Optional<Usuario> buscarPorLogin(String login);
	
	// salvar o usuario
	void salvar(Usuario usuario);
	
	// remover um registro de usuario
	void deletar(String id);
	
	// verificar se um usuario ja existe no sistema
	boolean existeLogin(String login);
	
}
