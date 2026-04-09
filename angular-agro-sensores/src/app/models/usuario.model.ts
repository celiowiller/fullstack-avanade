import { UserRoleModel } from "./user-role.model"

// regras estabelecidas para os dados referentes ao usuario
export interface UsuarioModel {
    id: string  // o caractere : indica que estamos definindo para a propriedade um data type especifica - neste caso -> string 
    login: string
    senha: string
    role: UserRoleModel
}
