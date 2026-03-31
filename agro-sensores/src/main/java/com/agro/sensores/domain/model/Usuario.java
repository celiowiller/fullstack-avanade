package com.agro.sensores.domain.model;

import com.agro.sensores.domain.enums.UserRole;
import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.ToString;
import lombok.Getter;

/*
// classe que representa o usuario no dominio (não é entidade/entity JPA)
// significa que esta classe descreve regras/constraint que devem ser aplicadas 
// aos dados referentes ao perfil de usuario - por exemplo: username, senha, entre outros;
public class Usuario {
	// Identificador unico do usuario
	private String id;
	
	// Login do usuario
	private String login;
	
	// senha do usuario
	private String senha;
	
	//definir a role do usuario
	private UserRole role;
	
	// definir o metodo construtor
	public Usuario(
				String id,
				String login,
				String senha,
				UserRole role
			) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}*/

@Getter
@ToString(exclude = "senha")
public class Usuario{
	// Identificador unico do usuario
		private String id;
		
		// Login do usuario
		private String login;
		
		// senha do usuario
		private String senha;
		
		//definir a role do usuario
		private UserRole role;
	
		public Usuario(
				String id,
				String login,
				String senha,
				UserRole role
				) {
			if(login == null || login.isBlank()) {
				// vamos lançar nossa exception custom
				throw new ValidacaoException("Login é obrigatorio!");
			}
			
			if(senha == null || senha.length() < 6) {
				throw new ValidacaoException("Sua senha deve ter pelo menos 6 caracteres");
			}
			
			this.id = id;
			this.login = login;
			this.senha = senha;
			this.role = role; 
		}
		
		public boolean isAdmin() {
			return this.role == UserRole.ADMIN;
		}
}