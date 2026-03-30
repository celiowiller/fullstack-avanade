package com.agro.sensores.domain.enums;

// Enum que define os "papeis/perfis" de usuarios do sistema
public enum UserRole {
	// Usuario administrador com acesso total ao sistema
	ADMIN("admin"),
	
	// Usuario comum com acesso limitado
	USER("user");
	
	// campo/propriedade que irá armazenar o valor textual da role
	private String role;
	
	// construtor do enum que receberá o valor da role
	UserRole(String role){
		this.role = role;
	}
	
	// definir o métodor de acessp/getter para que seja possivel acessar a role
	public String getRole() {
		return role;
	}
	
	// UserRole.ADMIN.getRole() -> "admin";
}
