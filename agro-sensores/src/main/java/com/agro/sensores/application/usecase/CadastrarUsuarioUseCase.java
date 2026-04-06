package com.agro.sensores.application.usecase;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.agro.sensores.domain.enums.UserRole;
import com.agro.sensores.domain.exception.RegraNegocioException;
import com.agro.sensores.domain.model.Usuario;
import com.agro.sensores.domain.repository.UsuarioRepository;


import jakarta.validation.constraints.NotNull;

// Caso de uso responsável por cadastrar usuário
@Service
@Validated
/*
 Para que o Spring intercepte a chamada do método e valide os dados 
 antes de executar a lógica de cadastro, 
 a classe @Service precisa da anotação @Validated
 */
@RequiredArgsConstructor
public class CadastrarUsuarioUseCase {

    // Repositório do domínio
    private final UsuarioRepository repository;

    // Encoder de senha
    private final PasswordEncoder passwordEncoder;

    // Método principal do caso de uso
    public void executar(String login, String senha, @NotNull UserRole userRole) {

        // Verifica se login já existe
        if (repository.existeLogin(login)) {
            throw new RegraNegocioException("Login já está em uso.");
        }

        // Criptografa senha
        String senhaCriptografada = passwordEncoder.encode(senha);

        // Cria usuário de domínio
        // Forma adequada: passando o objeto userRole diretamente
        Usuario usuario = new Usuario(null, login, senhaCriptografada, userRole);

        // Salva usuário
        repository.salvar(usuario);
    }
}
