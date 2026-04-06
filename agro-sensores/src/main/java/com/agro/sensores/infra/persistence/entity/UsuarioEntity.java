package com.agro.sensores.infra.persistence.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agro.sensores.domain.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


// as classes entity - dentro do pacote entity - são 
// "entidades" representativas das tables do db que estamos usando
// estas "entidades" irão representar as tables do db para a nossa aplicação

// "entidade" representativa JPA da table de usuarios no DB
@Entity
@Table(name = "usuarios")
@Getter
@Setter


// @NoArgsConstructor //- esta annotation gera automaticamente um construtor-padrão para 
// a classe de referencia public Usuario(){}

 @AllArgsConstructor // - esta annotation gera um construtor com todos os campos/fields
// da classe como argumentos

//@Data // "engloba" tudo: a controversias - na verdade @Data ele é um "pacote" 
//que tem proposito em participar de outras funcionalidades.

@EqualsAndHashCode(of = "id") // aqui, a annotation indica que o hash code precisar ser igual
// ao indicado no id do usuario

public class UsuarioEntity implements UserDetails{

	// 1. Adicionando a linha do "RG" da classe
    private static final long serialVersionUID = 1L;
	
	
	//id unico gerado automaticamente
	@Id // annotation que indica a chave-primaria da table
	@GeneratedValue(strategy = GenerationType.UUID) // aqui, estamos indicando que os Ids 
	// dos registros da table serão salvos e incrementados de forma automatizada
	private String id;	
	
	// login do usuario
	@Column(nullable=false, unique = true)
	private String login;
	
	// senha criptografada
	@Column(nullable=false)
	private String senha;
	
	// roles de usuario
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private UserRole role;
	
	
	// =============== IMPLEMENTAÇÃO DO getAuthorities() ===================
	public Collection<? extends GrantedAuthority> getAuthorities(){
		// se nosso usuario for um ADMIN, ele "ganha" todas as permissões. 
		// se for USER, "ganha" somente a permissão de usuario comum
		if(this.role == UserRole.ADMIN) {
			return List.of(
						new SimpleGrantedAuthority("ROLE_ADMIN"),
						new SimpleGrantedAuthority("ROLE_USER")
					);
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}
	
	public UsuarioEntity() {}

	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}
	
	public boolean isAccountNonExpired() {
		return true; // conta não expirada
	}
	
	public boolean isAccountNonLocked() {
		return true; // conta não bloqueada
	}
	
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return true; // usuario está ativo
	}
}
