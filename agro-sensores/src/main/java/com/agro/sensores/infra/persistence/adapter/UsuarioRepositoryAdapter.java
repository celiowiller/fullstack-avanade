package com.agro.sensores.infra.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.model.Usuario;
import com.agro.sensores.domain.repository.UsuarioRepository;
import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import lombok.RequiredArgsConstructor;

// o que é um adapter: seu objetivo é "transformar/converter\" o domain <-> entity
// este é um elemento FUNDAMENTAL DA ARQUITETURA HEXAGONAL
@Component
@RequiredArgsConstructor
/*public UsuarioRepositoryAdapter(JpaUsuarioRepository jpa){
 * 	this.jpa = jpa
 * }
 * */
public class UsuarioRepositoryAdapter implements UsuarioRepository { // ESTE É NOSSO 
	// PERSISTENCE ADAPTER
	
	// este adapter irá conectar o dominio(domain) com o DB
	
	// repositorio jpa: definir o elemento de referencia
	// ESTE É O ELEMENTO DE REFERENCIA PARA A INJEÇÃO DE DEPENDENCIA!
	private final JpaUsuarioRepository jpa;
	
	// Buscar por ID
	public Optional<Usuario> buscarPorId(String id){
		return jpa.findById(id).map(this::toDomain);
	}
	
	// Buscar pelo login
	public Optional<Usuario> buscarPorLogin(String login){
		return jpa.findByLogin(login).map(this::toDomain);
	}
	
	// salvar usuario
	public void salvar(Usuario usuario) {
		jpa.save(toEntity(usuario));
	}
	
	// remover/excluir um usuario - devidamente identificado
	public void deletar(String id) {
		jpa.deleteById(id);
	}
	
	// verificar a existencia de dados de credenciais - especificamente o dado login
	public boolean existeLogin(String login) {
		return jpa.findByLogin(login).isPresent();
	}
	
	// processo/bloco que converte entidade <-> dominio 
	// são conhecidos como Mappers - este métodos atuam como se fossem "tradutores" 
	private Usuario toDomain(UsuarioEntity entity) {
		// expressão de retorno do método. É na expressão de retorno que a "conversão" se dá
		return new Usuario(
					entity.getId(),
					entity.getLogin(),
					entity.getSenha(),
					entity.getRole()
				);
	}
	
	// "conversão" de domain/dominio em entity
	private UsuarioEntity toEntity(Usuario usuario) {
		return new UsuarioEntity(
					usuario.getId(),
					usuario.getLogin(),
					usuario.getSenha(),
					usuario.getRole()
				);
	}
}
