export interface Leitura {
  id?: number          // O '?' indica que é opcional (o banco gera no POST)
  valor: number
  dataHora: string     // Recebemos como ISO String do LocalDateTime do Java
  localizacao: string
}

// src/app/models/leitura.model.ts
export interface SensorComLeituras {
  id: string
  nome: string
  localizacaoAtual: string  // ← Altere de "localizacao" para "localizacaoAtual"
  leituras: Leitura[]
  historico?: any[]
}