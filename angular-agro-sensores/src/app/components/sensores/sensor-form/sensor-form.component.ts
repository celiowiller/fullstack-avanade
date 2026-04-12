import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MaterialModule } from '../../../shared/material/material.module';
import { SensorService } from '../../../services/sensor.service';

@Component({
  selector: 'app-sensor-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
  templateUrl: './sensor-form.component.html',
  styleUrls: ['./sensor-form.component.css']
})
export class SensorFormComponent {
  private fb = inject(FormBuilder)
  private sensorService = inject(SensorService)
  private router = inject(Router)

  // Definição das propriedades do formulário baseadas no CriarSensorDTO do Java
  public sensorForm = this.fb.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    tipo: ['', [Validators.required]],
    localizacao: ['', [Validators.required]],
    ativo: [true] // Sensores novos começam ativos por padrão na rede AERS
  })

aoSalvar(): void {
  if (this.sensorForm.valid) {
    // Criamos um objeto limpo para o DTO
    const novoSensor = {
      nome: this.sensorForm.value.nome,
      tipo: this.sensorForm.value.tipo, // Verifique se o valor aqui é igual ao Enum do Java
      localizacao: this.sensorForm.value.localizacao,
      ativo: !!this.sensorForm.value.ativo // Garante que é boolean
    }

    this.sensorService.salvar(novoSensor as any).subscribe({
      next: () => this.router.navigate(['/sensores']),
      error: (err) => {
        console.error('Erro detalhado do servidor:', err)
        // O erro 500 costuma ter uma mensagem no corpo da resposta
      }
    })
  }
}

  cancelar(): void {
    this.router.navigate(['/sensores'])
  }
}