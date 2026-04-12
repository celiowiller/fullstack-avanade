import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SensorComLeituras } from '../models/leitura.model';

@Injectable({
  providedIn: 'root'
})
export class LeituraService {
  private http = inject(HttpClient)
  
  /**
   * URLs base configuradas conforme o seu ecossistema Backend.
   * API_SENSORES: lida com os dados estruturais e agregados.
   * API_TELEMETRIA: lida estritamente com o fluxo de dados dos sensores.
   */
  private readonly API_SENSORES = 'http://localhost:8080/sensores'
  private readonly API_TELEMETRIA = 'http://localhost:8080/telemetria'

  /**
    Traz a lista completa de sensores com seus históricos e medições.
    Consome o @GetMapping("/com-leituras") do seu SensorController.
   */
  obterDashboardCompleto(): Observable<SensorComLeituras[]> {
    return this.http.get<SensorComLeituras[]>(`${this.API_SENSORES}/com-leituras`)
  }

  /**
    MÉTODO ASSÍNCRONO: registrarLeitura
    Registra uma nova medição de campo enviada pelo Simulador ou Dispositivo.
    Consome o @PostMapping do seu TelemetriaController no Spring Boot.
    parametro dto Objeto contendo { sensorId, valor, dataHora } conforme o LeituraRequestDTO.
   */
  registrarLeitura(dto: any): Observable<void> {
    // Esta chamada aciona o RegistrarLeituraUseCase no seu Backend
    return this.http.post<void>(this.API_TELEMETRIA, dto)
  }
}