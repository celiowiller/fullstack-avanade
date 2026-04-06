package com.agro.sensores.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agro.sensores.api.dto.request.UsuarioRequestDTO;
import com.agro.sensores.application.usecase.CadastrarUsuarioUseCase;


// Controller de usuários
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CadastrarUsuarioUseCase useCase;

    // Endpoint de cadastro
    @PostMapping
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid UsuarioRequestDTO dto) {

    	// Agora o Java reconhece que dto.role() é um UserRole
        // e o método executar do UseCase também espera um UserRole.
        useCase.executar(dto.login(), dto.senha(), dto.role());

        // Retorna HTTP 201 (Created)
        return ResponseEntity.status(201).build();
    }
}
