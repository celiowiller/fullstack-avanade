import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MaterialModule } from '../../../shared/material/material.module';
import { SensorService } from '../../../services/sensor.service';
import { LeituraService } from '../../../services/leitura.service';
import { SensorModel } from '../../../models/sensor.model';

@Component({
  selector: 'app-telemetria-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MaterialModule],
  templateUrl: './telemetria-form.component.html',
  styleUrls: ['./telemetria-form.component.css']
})
export class TelemetriaFormComponent implements OnInit {
  private fb = inject(FormBuilder)
  private sensorService = inject(SensorService)
  private leituraService = inject(LeituraService)
  private snackBar = inject(MatSnackBar)

  // Lista de sensores para o select
  sensores = signal<SensorModel[]>([])

  // Formulário alinhado ao LeituraRequestDTO do Java
  telemetriaForm = this.fb.group({
    sensorId: ['', Validators.required],
    valor: [null, [Validators.required]],
    dataHora: [this.getHoraLocal(), Validators.required]// Formato para o datetime-local
  })

  // Função para formatar a data/hora local sem o offset de 3 horas do ISO
  private getHoraLocal(): string {
    const agora = new Date()
    agora.setMinutes(agora.getMinutes() - agora.getTimezoneOffset())
    return agora.toISOString().slice(0, 16)
  }
  ngOnInit(): void {
    // Carregamos os sensores para que a aluna possa escolher qual vai "enviar" o dado
    this.sensorService.buscarTodos().subscribe(dados => this.sensores.set(dados))
  }


  enviarLeitura(): void {
    if (this.telemetriaForm.valid) {
      // ESTE É O PAYLOAD QUE O JAVA EXIGE (LeituraRequestDTO)
      const payload = {
        sensorId: this.telemetriaForm.value.sensorId, 
        valor: this.telemetriaForm.value.valor,       
        dataHora: this.telemetriaForm.value.dataHora  
      };

      console.log('Payload enviado:', payload)

      // Chame o método correto do service: registrarLeitura
      this.leituraService.registrarLeitura(payload).subscribe({
        next: () => {
          alert('Telemetria enviada com sucesso!');
          this.telemetriaForm.reset({ dataHora: this.getHoraLocal() })
        },
        error: (err) => {
          console.error('Erro 400 - Detalhes do Backend:', err.error)
        }
      })
    }
  }
}
