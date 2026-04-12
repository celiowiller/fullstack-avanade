// este é o recurso necessario para a criação do interceptor; recurso com origem no contexto de estrutura logica funcional do angular
// seu proposito é: interceptar requisições HTTP e adicionar, a partir da interceptação, de forma automatica, o token
import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => { // a definição do callback

  // definir uma const para receber como valor o token 
  const token = localStorage.getItem('token') // aqui obtemos o token 
  // verificação simples de existencia de valor atribuido a const
  if(token){
    const clonada = req.clone({ // req: aqui, estmaos "copiando" o contexto da requisição que pode ser composta pela URL, VERBOS, bbody, PARAMS... REQ.HEADERS.SET()
      setHeaders: {Authorization: `Bearer ${token}`} // aqui, cria uma cópia e, assim, podemos modifica-la 
    })
    return next(clonada) // nós, aqui, estamos enviando a requisição, agora, modificada
  }
  return next(req) // caso o token não exista, enviamos a requisição original sem modificação
}
