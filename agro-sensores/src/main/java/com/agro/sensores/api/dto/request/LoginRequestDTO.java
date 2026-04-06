package com.agro.sensores.api.dto.request;

import jakarta.validation.constraints.NotBlank;

//DTO de entrada para login
public record LoginRequestDTO(

     // Login do usuário
     @NotBlank String login,

     // Senha do usuário
     @NotBlank String senha
) {}
