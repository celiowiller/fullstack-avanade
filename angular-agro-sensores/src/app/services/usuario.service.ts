// aqui, temos a importação do recurso Injectable. O uso deste recurso indica que esta classe é PASSIVEL - esta apta -  a receber uma DI - dependency injection(injeção de dependencia) 
import { HttpClient } from '@angular/common/http'; // este é o recurso necessario para que seja possivel criar a injenção de dependencia para as requisições http -POST, PUT, GET, DELETE

import { Injectable, inject, signal, computed } from '@angular/core';
import { UsuarioModel } from '../models/usuario.model';
// inject: recurso necessario para a pratica de injeção de dependencia 
import { Observable, tap } from 'rxjs';
// portanto, tomando como referencia a analise feita do decorator @Componet, tudo aquilo que está descrito abaixo do 
// decorator @Injectable esta apto a receber uma injeção de dependencia.

import { jwtDecode } from 'jwt-decode'; // recurso para auxiliar no "distrinchiamento" - verbet criado por Nelson Fornazeiro


@Injectable({
  providedIn: 'root' // este trecho é fundamental para as aplicações angular "modernas"; porque, aqui, temos aquilo que vamos de "gerencimento de singletons". Estamos dizendo ao Angular que: este service deve ser uma unica instancia que será "compartilhada" por todo e qualquer componente que quiser fazer uso das intruções aqui descritas.
})

// este service é responsavel por processor de cadastro e login do usuario
export class UsuarioService {
  /*
  ==================================================================
    1º BLOCO: IMPLEMENTAR OS RECURSOS NECESSARIOS PARA AS OPERAÇÕES
    IMPLEMENTADAS AQUI, NESTE SERVICE
  ==================================================================
  */

  //  1º passo: definir uma propriedade que receberá como valor o método inject() com oa Class HttpClient como argumento para que seja fazer as requisições http necessarias
  private http = inject(HttpClient) // AGORA, podemos usar a propriedade http como injeção de dependencia para fazer as requisições necessarias

  // 2º passo: agora, vamos definir uma nova propriedade que receberá como valor a URL base para a integração do front com a API adequada - API de cadastro e login
  private readonly ApiURL: string = 'http://localhost:8080' // neste momento, "integramos" o nosso front como o nosso backend

  // *** EXTRA: aqui, vamos utlizar o conceito e o recurso de SIGNAL DE ESTADO/STATE -> este recurso vai nos auxiliar na observação do estado dos dados do usuario: por exemplo, se o usuario esta, ou não, atutenticado; para eeste proposito, podemos verificar a existencia do token e se esta armazenado no localStorage.

  private _estaLogado = signal<boolean>(this.verificarToken())
  //  private _estaLogado = signal<boolean>(this.verificarToken())

  // *** EXTRA - AGORA, PRECISAMOS "EXPOR" O SIGNAL; porque, dessa forma, podemos acessar o estado dos dados do usuario - a partir de outros componentes, se assim for necessario;
  public estaLogado = computed(() => this._estaLogado())

  constructor() { }

  /*
  ==================================================================
    2º BLOCO: IMPLEMENTAR AS OPERAÇÕES NECESSARIAS PARA O SERVICE
  ==================================================================
  */

  // 1º operação assincrona: definir o método/tarefa assincrona/promise para o cadastro de registro de um usuario
  // http://localhost:8080/usuarios
  cadastrar(usuario: UsuarioModel): Observable<void>{
    return this.http.post<void>(`${this.ApiURL}/usuarios`, usuario)

    //  Observable<void>: padrão classico - no Angular -  para a comunicação com APIs REST. Como "comportamento" padrão o método não tem compromisso de retornar um contexto de "corpo de requisição" em formato JSON - os dados que forma cadsatrado, simplesmente cumpre uma tarefa de "chamar/acessar" a API para a execução da inserção do cadastro do usuario, na base

    // http: esta é a injecção de dependencia para a construção da requisição http para a API e o endpoint especifico

    // post<void>: esta é a requisição HTTP feita para a API

    // post<void>(`${this.ApiURL}/usuarios`): aqui, estamos fazendo uma requisição HTTP POST para a API de cadastro do usuario - java springboot. /usuarios é o endpoint especifico da API 
  }

  // 2º operação assincrona: definir o método/tarefa assincrona/promise para o login de um usuario
  // http://localhost:8080/auth/login
  login(credenciais: Partial<UsuarioModel>): Observable<{ token: string}>{
    return this.http.post<{token: string}>(`${this.ApiURL}/auth/login`, credenciais)
      .pipe( // este é um método - pipe() - que nos auxilia a estabelecer uma "comunição assincrona direta" com a API para que caso ocorra alguma interrupção, ao ser reestabelecida, podemos completa-la
        tap( // este método obtem o fluxo dos dados do Observable e armazena este token obtido no localStorage - no front - para que, dessa forma, o usuario possa estabelecer a navegação pelas areas restritas as quais ele tem acesso 
          resposta => { localStorage.setItem('token', resposta.token)

              // *** EXTRA: agora, podemos - fazendo uso do signal - "observar" se há alguma alteração no estado de dados do usuario
              this._estaLogado.set(true)
          }
        )
      )
  }

  // // 2º operação assincrona: definir o método/tarefa assincrona/promise para o logout de um usuario
  logout(): void{
    localStorage.removeItem('token')// aqui, para fazer o logout do usuario estamos "limpando" o localStorage e, portanto, "matando" a sessão que foi estabelecida quando o usuario fez seu login e o token foi armazenado

    // precisamos referenciar o signal para que ocorra a "notificação" de estado dos dados de usuario: de true (usuario logado) para false(usuario "deslogado")
    this._estaLogado.set(false)
  }

  // 3º operação: definir o método para verificar se existe o token armazenado no localStorage
  private verificarToken(): boolean{
    return !!localStorage.getItem('token')
    // !!localStorage.getItem('token'): o uso do operador !! transforma qualquer valor numn valor boolean
    // "HUAHScmnc.amcnjsdkh45646" - TRUE
    // ! - INVERSOR LOGICO
    // !! - INVERTE DE NOVO
  } 

  // 4º OPERAÇÃO -  obter o token a partir do storage
  obterToken(): string | null{
    return localStorage.getItem('token')
  }

  // 5º passo: recuperar o nivel de acesso de usuario armazenado no processo de login; para o seguinte proposito -> 
  // ESTA SERIA UMA REDUNDANCIA, OU NÃO?
  obterRole(): string | null{
    const token = localStorage.getItem('token')

    if(!token) return null

    const decode: any = jwtDecode(token)
    
    return decode.role || null

    //return localStorage.getItem('role')
  }
 
}
