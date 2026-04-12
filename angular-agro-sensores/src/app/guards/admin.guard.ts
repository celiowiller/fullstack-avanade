import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';
import { UserRoleModel } from '../models/user-role.model';

/**
 Guarda de Administrador (Governança de Operação)
 Objetivo: Restringir ações sensíveis (Cadastro/Exclusão) apenas para o perfil ADMIN.
 */
export const adminGuard: CanActivateFn = (route, state): boolean | UrlTree => {
  const servicoUsuario = inject(UsuarioService);
  const roteador = inject(Router);

  // Recuperamos a Role diretamente do serviço
  const perfilUsuario = servicoUsuario.obterRole();

  if (servicoUsuario.estaLogado() && perfilUsuario === UserRoleModel.ADMIN) {
    return true;
  }

  // Notificação de barreira de segurança
  alert('ACESSO NEGADO: Você não possui privilégios de Administrador da rede AERS.');
  
  // Redireciona para a Home, já que o usuário está logado, mas não tem permissão aqui
  return roteador.parseUrl('/home');
};