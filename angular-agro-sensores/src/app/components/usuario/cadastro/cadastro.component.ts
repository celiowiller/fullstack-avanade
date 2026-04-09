import { Component, inject } from '@angular/core';

// a partir, vamos fazer um conjunto de importação de recursos para o pleno funcionamento do componente
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
// os recursos acima irão nos auxiliar na construção, obtenção e validação de valores inseridos, pelo usuario, no fomulario de cadastro
import { MaterialModule } from '../../../shared/material/material.module';
// importar o service
import { UsuarioService } from '../../../services/usuario.service';
import { UsuarioModel } from '../../../models/usuario.model';
/// import { Router } from 'express'; ESTE ERA O BENDITO QUE ESTAVA USANDO O MIME 
// O CORRETO É ESTE
import { Router } from '@angular/router';
import { UserRoleModel } from '../../../models/user-role.model';



@Component({
  selector: 'app-cadastro',
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {
  /*
  ====================================================================
    1º BLOCO: DEFINIÇÃO DE RECURSOS PARA AS OPERAÇÕES DO COMPONENTE
  ====================================================================
  */

  // 1º passo: definir o titulo do componente
  public tituloComp: string = 'Cadastro de usuario'
  public subtituloComp: string = 'Insira seus dados no formulario'

  // 2º passo: criar a DI a partir do service 
  private usuarioService = inject(UsuarioService)
  private fb = inject(FormBuilder)
  private router = inject(Router)

  // 3º passo: definir uma propriedade que receberá como valor o model UsuarioRoleModel
  public perfisDisponiveis = Object.values(UserRoleModel) // aqui, estamos "pegando" todos os valores descritos na enum - UserRoleModel - e transformando num array; este é o resultado -> ['ADMIN', 'USER'] - DESSA FORMA PODEMOS, QUANDO VINCULAR ESTES DADOS NA VIEW, USAR, POR EXEMPLO, A ESTRUTURA SELECT/OPTION

   /*
  ====================================================================
    2º BLOCO: DEFINIÇÃO DAS OPERAÇÕES DO COMPONENTE
  ====================================================================
  */

  // 1ª OPERAÇÃO: definir a propriedade que receberá como valor os pares que serão atribuidos dos dadosobtidos pelo formulario -> aqui, estamos operando com o model-driven form: significa que o comportamento do fomulario esta sendo controlado pela camada logica do componente
  cadastroFormulario = this.fb.group({
    login: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required, Validators.minLength(6)]] ,
    role: [UserRoleModel.USER, Validators.required]
  })

  // 2ª OPERAÇÃO: definir o método que irá "enviar" os dados de usuario para cadastro
  aoCadastrar(): void{
    // verificar se os dados de cadastro estão em conformidade para o envio 
    if(this.cadastroFormulario.valid){
      // se a avaliação for TRUE 
      // aqui, abaixo, neste momento, a tarefa assincrona/promise acaba de ser executada pela associação ao método subscribe() - ele é quem executa as tarefas assincronas/Observable - a partir o RxJS
      this.usuarioService.cadastrar(this.cadastroFormulario.value as any).subscribe({
        next: () => {
          this.router.navigate(['/login'])
        }
      })
    }
  }

  // 3ª OPERAÇÃO: definir o método que cancela o cadastro e nos redireciona para o a tela de login - caso o usuario desita de faze-lo
  cancelar(): void{
    this.router.navigate(['/login'])
  }
}
