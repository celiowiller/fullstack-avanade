// este é "protetor" de rotas de componentes que queremos manter com acesso restrito
// este, também, é um elemento funcional
import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

// o nosso vai veririfcar se o usuário possui o token valido e se suas credenciais estão adequadas para o acesso restrito
// UrlTree:  auxilia no contexto de redirecionamento do usuario - a depender de suas credenciais e a requisição que ele esta fazendo 
export const authGuard: CanActivateFn = (route, state) : boolean | UrlTree => {

  const usuarioService = inject(UsuarioService) // agora, temos a DI do serviço de usuario
  const router = inject(Router) // agora, temos a possibilidade de criar, se necessario, "rotas" de redirecionamento

  // verificação para observar se o usuario esta logado
  if(usuarioService.estaLogado()){
    return true
  }
  
  // caso o usuario não esteja logado, podemos redireciona-lo para a rota de componente de login
  return router.parseUrl('/login') 
  
  
  //return true; // retorno true - o usuario tem as credenciais de acesso adequadas
  // se não -false: o acesso do usuario é bloqueado
};
