// este é o componente responsavel por exibir a listagem de todos os sensores existente e monitorados
// para este proposito vaos iniciar fazendo as importações necessarias
import { Component, OnInit, inject, signal } from '@angular/core';
// OnInit: seu objetivo é nos auxiliar no "gerenciamento" do ciclo de vida do componente

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MaterialModule } from '../../../shared/material/material.module';
import { SensorService } from '../../../services/sensor.service';
import { SensorModel } from '../../../models/sensor.model';

@Component({
  selector: 'app-sensor-list',
  imports: [CommonModule, MaterialModule],
  templateUrl: './sensor-list.component.html',
  styleUrl: './sensor-list.component.css'
})
// OnInit: a partir da inicialização do componente, em seu LifeCycle, podemos indicr algo priopritario para a exibição em tela
export class SensorListComponent implements OnInit{
    /*
  ====================================================================
    1º BLOCO: DEFINIÇÃO DE RECURSOS PARA AS OPERAÇÕES DO COMPONENTE
  ====================================================================
  */
  // passo 0: definir o titulo do componente
  tituloComp: string = 'Rede de Sensores'

  // passo 1: vamos definir as injeções de dependencia
  private sensorService = inject(SensorService)
  private roteador = inject(Router)

  // passo 2: fazer uso dos signals
  listaDeSensores = signal<SensorModel[]>([])
  estaCarregando = signal<boolean>(false)

  // passo 3: precisamos definir um conjunto de dados que sera usado para definir as colunas da table
  colunasTabela: string[] = ['nome', 'tipo', 'localizacao', 'status', 'acoes']

   /*
  ====================================================================
    2º BLOCO: DEFINIÇÃO DAS OPERAÇÕES DO COMPONENTE
  ====================================================================
  */

  // passo 1: agora, vamos definir o uso do AngularHook - ngOnInit: seu objetivo é:
  // "priorizar" qualquer conteudo ou funcionalidade que nele esteja descrito; ou seja, assim que o componente inicia, OnInit, queremos que algo, de forma prioritaria, "esteja" disponivel para uso
  ngOnInit(): void {
    // agora,a vamos referenciar aqui, dentro do AngularHook ngOnInit, a chamada da função/método que irá carregar a lista de sensores obtidas pelo service - acessado a partir chamada da API
    this.carregarDadosDosSensores()  
  }

  // passo 2: definir o método/função que carrega dados no componente
  carregarDadosDosSensores(): void{
    this.estaCarregando.set(true)

    this.sensorService.buscarTodos().subscribe( // aqui, a tarefa assincrona foi executada
      {
        next: (dados: any) =>{
          this.listaDeSensores.set(dados) // aqui, estamos "passando" o valores recuperados da base para o array de dados para vincularmos na view
          this.estaCarregando.set(false) // aqui, uma vez que os dados foram carregados e passados para o array eles, então, não estão mais carregados
        },
        error: (erro: any) =>{
          console.error('Erro ao buscar sensores: ', erro)
          this.estaCarregando.set(false)
        }
      })
  }

  // passo 2: exclusão de sensor
  excluirSensor(id: string):void{
    if(confirm('Tem certeza que deseaj excluir, definitivamente, este sensor? ')){
      this.sensorService.deletar(id).subscribe({
        next: () =>{
          this.carregarDadosDosSensores()
        }
    })
    }
  }

  // passo 3: definir um método que redireciona o usuario para o componente de cadastro de um novo sensor
  navegarParaCadastro(): void{
    this.roteador.navigate(['/configuracoes/novo-sensor'])
  }
  // passo 4: definir um método que redireciona o usuario para o componente de ver detalhes de um sensor
  verDetalhes(id: string): void{
    this.roteador.navigate(['/sensores/detalhes', id])
  }
}
