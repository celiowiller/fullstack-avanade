package com.agro.sensores.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

// Enum que define os "papeis/perfis" de usuarios do sistema

import io.swagger.v3.oas.annotations.media.Schema;  // ← Import necessário

//Enum simples compatível com OpenAPI/SpringDoc
@Schema(description = "Papéis de usuário no sistema", allowableValues = {"ADMIN", "USER"})
public enum UserRole {
	// Usuario administrador com acesso total ao sistema
	@Schema(description = "Administrador")
	ADMIN("admin"),
	
	// Usuario comum com acesso limitado
	@Schema(description = "Usuário comum")
	USER("user");
	
	// campo/propriedade que irá armazenar o valor textual da role
	private String role;
	
	// construtor do enum que receberá o valor da role
	UserRole(String role){
		this.role = role;
	}
	
	// definir o métodor de acessp/getter para que seja possivel acessar a role
    @JsonValue
    @Schema(description = "Valor textual da role para serialização JSON")
	public String getRole() {
		return role;
	}
    
 // Método estático para desserialização (se necessário)
    @JsonCreator
    public static UserRole fromRole(String role) {
        for (UserRole r : values()) {
            if (r.role.equalsIgnoreCase(role)) return r;
        }
        throw new IllegalArgumentException("Role inválida: " + role);
    }
	
}
