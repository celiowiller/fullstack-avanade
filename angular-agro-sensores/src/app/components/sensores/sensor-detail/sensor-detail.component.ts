import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialModule } from '../../../shared/material/material.module';
import { SensorService } from '../../../services/sensor.service';
import { SensorModel } from '../../../models/sensor.model';

@Component({
  selector: 'app-sensor-detail',
  standalone: true,
  imports: [CommonModule, MaterialModule],
  templateUrl: './sensor-detail.component.html',
  styleUrls: ['./sensor-detail.component.css']
})
export class SensorDetailComponent implements OnInit {
  private route = inject(ActivatedRoute)
  private router = inject(Router)
  private sensorService = inject(SensorService)

  // Signal para armazenar os detalhes do sensor (espelhando o SensorModel completo)
  public sensor = signal<SensorModel | null>(null)
  public carregando = signal<boolean>(true)

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')
    if (id) {
      this.carregarDetalhes(id)
    }
  }

  carregarDetalhes(id: string): void {
    this.carregando.set(true)
    // Buscamos o sensor específico por ID
    this.sensorService.buscarPorId(id).subscribe({
      next: (dados) => {
        this.sensor.set(dados)
        this.carregando.set(false)
      },
      error: (err) => {
        console.error('Erro ao recuperar detalhes do sensor:', err)
        this.carregando.set(false);
      }
    })
  }

  voltar(): void {
    this.router.navigate(['/sensores'])
  }
}