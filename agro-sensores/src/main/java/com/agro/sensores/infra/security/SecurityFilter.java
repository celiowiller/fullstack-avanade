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
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final JpaUsuarioRepository repository;

    @Override // Adicione esta anotação para garantir que está sobrescrevendo o método certo
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {
            String login = tokenService.getSubject(token);
            var usuarioOpt = repository.findByLogin(login);

            if (usuarioOpt.isPresent()) {
                var usuario = usuarioOpt.get();
                var authentication = new UsernamePasswordAuthenticationToken(
                    usuario, 
                    null, 
                    usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
        
    private String recuperarToken(HttpServletRequest request) {
        // 1. Busca o cabeçalho 'Authorization'
        String authorizationHeader = request.getHeader("Authorization");

        // 2. Se estiver vazio ou não começar com "Bearer ", retorna null (não tenta tratar)
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        // 3. Remove o "Bearer " e retorna apenas o código do token limpo
        return authorizationHeader.substring(7); 
    }  
    }

