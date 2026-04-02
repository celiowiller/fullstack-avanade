package com.agro.sensores.infra.persistence.entity;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
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

// em função de usarmos o contexto do Spring Security é necessario, aqui, implementarmos a 
// interface UserDetails - esta interface "obriga" a existencia do método getAuthorities()
// Caso não usemos esta abordagem, corremos o risco de o Spring não "saber" como extrair
// as roles(papeis)  do nosso objeto para autorizar o acesso as nossas rota "privadas"
public class UsuarioEntity implements UserDetails{

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

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true; // conta não expirada
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true; // conta não bloqueada
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true; // usuario está ativo
	}
}

/*
 *  Collection<? extends GrantedAuthority>: o spring não esta "preocupado" com qual é 
 *  o tipo de conjunto de dados que ele vai devolver. Por isso a nossa coleção/collection
 *  é generica. A expressão - ? extends GrantedAuthority - significa que, seja qual for o
 *  objeto, que implemente a interface atende ao Spring 
 *  
 *  SimpleGrantedAuthority: esta é a implementação padrão do Spring para uma permissão
 *  simples baseada em texto. Porque o Spring Security é "doido" numa string.
 *  
 *  // =============== IMPLEMENTAÇÃO DO getAuthorities() para 6+ niveis ===================
 *  
 *  definir uma entity par ao proposito de armazenamento das permissoes
 *  usando a relação @ManyToMany e iterando sobre as roles
 *  
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return permissoes.stream()
			.map(p -> new SimpleGrantedAuthority(p.geNome()))
			.collect(Collectors.toList())
	}
 *  
 * */
