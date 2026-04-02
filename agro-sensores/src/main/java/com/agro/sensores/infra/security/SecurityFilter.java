package com.agro.sensores.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

// este será o nosso "segurança" da porta; ou seja, este contexto irá interceptar 
// qualquer requisição que for feita para a API - antes de chegar a qualqeur endpoint
// porque precisamos saber "quem" esta tentando acessar noss aaplicação;

@Component
@RequiredArgsConstructor
// ao praticar o mecanismo de herança, abaixo, estamos tentando garantir que este processo
// de validação aconteça, extamente, uma vez por requisição - para evitar processamentos
// redundantes
public class SecurityFilter extends OncePerRequestFilter{
	// definindo as DIs
	private final TokenService tokenService;
	private final JpaUsuarioRepository repository;
	
	protected void doFilterInternal(
			HttpServletRequest request,  // este é o "pedido" da aplicação client
			HttpServletResponse response, // este é a "resposta" do servidor
			FilterChain filterChain // esta é a "sequencia de "filtragem"
			) throws ServletException, IOException { // estão as referencia de exceções - caso
		// algm problema que ser encontrado por este metodo - venha a ocorrer
		
		// aqui, vamos recuperar o token do header
		String token = recuperarToken(request);
		
		// verificar se o token, realmente, existe
		if(token != null) {
			// extraindo o token
			String login = tokenService.getSubject(token);
			
			// buscando o usuario do DB
			var usuario = repository.findByLogin(login);
			
			if(usuario.isPresent()) { // aqui, é o momento onde nosso "segurança" 
				// abre a porta, carimba/autoriza a passagem/passaporte do usuario.
				// criar/definir o processo de autenticação
				var authentication = new UsernamePasswordAuthenticationToken(
							usuario.get(), // é o objeto de usuario
							null, // normalmente, neste local, seria referencia a senha de usuario
							// como o usuario ja foi validado - via JWT - não precisamos mais
							// desta referencia, por isso, passamos null
							usuario.get().getAuthorities() // aqui, estão as permissoes!
							// são a roles; é aqui esta sendo lido as roles ADMIN e USER
						);
				// define o usuario autenticado no contexto
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	// definir o método recuperarToken()
	private String recuperarToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header == null) {
			return null;
		}
		
		return header.replace("Bearer ", "");
	}
}
