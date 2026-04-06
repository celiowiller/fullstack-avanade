package com.agro.sensores.api.dto.request;

import com.agro.sensores.domain.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//DTO de criação de usuário
public record UsuarioRequestDTO(

     @NotBlank String login,
     @NotBlank String senha,
     @NotNull UserRole role
) {}
