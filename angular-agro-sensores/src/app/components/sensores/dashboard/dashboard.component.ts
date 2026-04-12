import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../shared/material/material.module';
import { LeituraService } from '../../../services/leitura.service';
import { SensorComLeituras } from '../../../models/leitura.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, MaterialModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private leituraService = inject(LeituraService)

  // Signal que armazena a lista de sensores vinda do UseCase
  public listaSensoresComLeituras = signal<SensorComLeituras[]>([])
  public carregando = signal<boolean>(true)

  ngOnInit(): void {
    this.carregarDados()
  }

  carregarDados(): void {
    this.carregando.set(true);
    this.leituraService.obterDashboardCompleto().subscribe({
      next: (dados) => {
        // DEBUG:  JSON completo
      console.log('JSON do Backend:', JSON.stringify(dados, null, 2));
        
        // DEBUG: Verificar especificamente a localização atual
        dados.forEach(s => {
          console.log(`Sensor "${s.nome}" | localizacao ATUAL: "${s.localizacaoAtual}"`)
        })
        this.listaSensoresComLeituras.set(dados)
        this.carregando.set(false)
      },
      error: (err) => {
        console.error('Erro ao carregar dashboard:', err)
        this.carregando.set(false)
      }
    })
  }
}