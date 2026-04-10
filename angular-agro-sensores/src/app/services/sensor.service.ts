import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SensorModel } from '../models/sensor.model';

@Injectable({
  providedIn: 'root'
})
export class SensorService {
  /**
   * Injeção do HttpClient utilizando o padrão funcional.
   * Não utilizamos mais o construtor para manter a classe limpa e moderna.
   */
  private readonly http = inject(HttpClient);

  /**
   * URL base do backend Spring Boot definida no seu ecossistema.
   * Alinhado com o mapeamento @RequestMapping("/sensores") do seu Controller.
   */
  private readonly API_URL = 'http://localhost:8080/sensores';

  /**
   * Recupera a lista completa de sensores cadastrados no banco de dados.
   * Conecta-se ao método buscarTodos() do seu SensorRepository.
   * retornando um Observable contendo um array de objetos Sensor.
   */
  buscarTodos(): Observable<SensorModel[]> {
    return this.http.get<SensorModel[]>(this.API_URL);
  }

  /**
   * Busca os detalhes de um sensor específico através do seu Identificador Único (UUID).
   * parametro id Identificador do sensor.
   * retornando um Observable de um único objeto Sensor.
   */
  buscarPorId(id: string): Observable<SensorModel> {
    return this.http.get<SensorModel>(`${this.API_URL}/${id}`);
  }

  /**
   * Persiste um novo sensor no sistema ou atualiza um existente.
   * Este método aciona a lógica de validação do seu domínio Java (ValidacaoException).
   * parametro sensor Objeto contendo os dados do sensor (nome, tipo, localização, ativo).
   * retornando Observable do sensor persistido com o ID gerado pelo banco.
   */
  salvar(sensor: SensorModel): Observable<SensorModel> {
    return this.http.post<SensorModel>(this.API_URL, sensor);
  }

  /**
   * Remove um sensor do sistema de telemetria.
   * parametro id Identificador do sensor a ser removido.
   * retornando Observable void indicando o sucesso da operação.
   */
  deletar(id: string): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }

  /**
   * Alterna o estado de ativação do sensor.
   * parametro id Identificador do sensor.
   * parametro status Boolean indicando se o sensor deve ser ativado ou desativado.
   */
  alterarStatus(id: string, status: boolean): Observable<SensorModel> {
    // Exemplo de PATCH para economia de banda em telemetria
    return this.http.patch<SensorModel>(`${this.API_URL}/${id}/status`, { ativo: status });
  }
}