package com.agro.sensores.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

// este será o nosso "guardião" da palicação. Irá operar o contexto de seguraça bseada em 
// Tokens.
// vamos, para este proposito, implementar o conceito JWT: funciona como um "crachá" 
// composto com as credendicais necessarias para a autenticação/autorização de acesso do usuario0
@Service
public class TokenService {
	// chave secreta do JWT (posteriormente, vamos definir sua origem no arquivo
	// application.properties)
	//@Value("${api.security.secret}")
	 //Fallback direto no @Value - resolve o erro de placeholder
	@Value("${api.security.secret:default-dev-secret-123456}")
	private String secret;
	
	// agora, vamos criar o processo que gera o token
	public String gerarToken(UsuarioEntity usuario) {
		// Definição do algoritmo de assinatura do token
		Algorithm algoritmo = Algorithm.HMAC256(secret);
		
		// criar o token com as infos do usuario
		return JWT.create()
				.withIssuer("agro-sensores") //emissor
				.withSubject(usuario.getLogin()) //Usuário
				.withClaim("role", usuario.getRole().name()) //role do usuario "embarcado" no token
				.withExpiresAt(dataExpiracao()) // data de expiração do token
				.sign(algoritmo);
	}
	
	// validar e extrair o dado de login do token
	public String getSubject(String token) {
		// Definição do algoritmo de assinatura do token
		Algorithm algoritmo = Algorithm.HMAC256(secret);
		
		return JWT.require(algoritmo)
				.withIssuer("agro-sensores")
				.build()
				.verify(token)
				.getSubject();
	}
	
	// definição tempo de experição do token
	private Instant dataExpiracao() {
		return LocalDateTime.now()
				.plusHours(2)
				.toInstant(ZoneOffset.of("-03:00"));
				// acima, estamos definindo a data de validade do token. O argumneto 
		// -03:00 ajusta o horario para o fuso de Brasilia. (UTC-3). 
	}
}
