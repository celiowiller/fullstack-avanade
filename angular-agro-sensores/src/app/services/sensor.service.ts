import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SensorModel } from '../models/sensor.model';

@Injectable({
  providedIn: 'root'
})
export class SensorService {
  private readonly http = inject(HttpClient);
  private readonly API_URL = 'http://localhost:8080/sensores'

  /** 1. LISTAR TODOS */
  buscarTodos(): Observable<SensorModel[]> {
    return this.http.get<SensorModel[]>(this.API_URL)
  }

  /** 5. BUSCAR POR ID */
  buscarPorId(id: string): Observable<SensorModel> {
    return this.http.get<SensorModel>(`${this.API_URL}/${id}`)
  }

  /** 1. CRIAR SENSOR (ADMIN) */
  salvar(sensor: Partial<SensorModel>): Observable<void> {
    return this.http.post<void>(this.API_URL, sensor)
  }

  /** 2. ATUALIZAR NOME (ADMIN) 
      Alinhado com AtualizarSensorUseCase e o DTO AtualizarSensorDTO 
  */
  atualizarNome(id: string, nome: string): Observable<void> {
    return this.http.put<void>(`${this.API_URL}/${id}`, { nome })
  }

  /** 7. ATUALIZAR LOCALIZAÇÃO (ADMIN)
      Aciona o UseCase que encerra a localização antiga e cria uma nova (Temporal)
  */
  atualizarLocalizacao(id: string, localizacao: string): Observable<void> {
      return this.http.put<void>(`${this.API_URL}/${id}/localizacao`, {
      localizacao: localizacao
    })
  }

  /** 6. DELETAR SENSOR (ADMIN) */
  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`)
  }

  /** * EXTRA: BUSCAR COM LEITURAS 
   * Consome o endpoint 4 do seu Controller
   */
  buscarComLeituras(): Observable<any[]> {
    return this.http.get<any[]>(`${this.API_URL}/com-leituras`)
  }
}