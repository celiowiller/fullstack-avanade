package com.agro.sensores.infra.persistence.entity;

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

public class UsuarioEntity {

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
	
	public UsuarioEntity() {}
}
