package com.agro.sensores.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;
import com.agro.sensores.infra.security.TokenService;

// Caso de uso de autenticação (login)
@Service
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {

    // Gerenciador de autenticação do Spring
    private final AuthenticationManager authenticationManager;

    // Repositório JPA
    private final JpaUsuarioRepository repository;

    // Serviço de token JWT
    private final TokenService tokenService;

    // Método que executa autenticação
    public String executar(String login, String senha) {

        // Cria objeto de autenticação
        var authToken = new UsernamePasswordAuthenticationToken(login, senha);

        // Autentica usuário
        authenticationManager.authenticate(authToken);

        // Busca usuário no banco
        UsuarioEntity usuario = repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Gera token JWT
        return tokenService.gerarToken(usuario);
    }
}