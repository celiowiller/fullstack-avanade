import { Leitura } from './leitura.model';

export enum TipoSensor {
    SOLO = 'SOLO',
    CLIMA = 'CLIMA',
    NIVEL_TANQUE = 'NIVEL_TANQUE',
    PH = 'PH'
}

/**
  Representa o histórico de movimentação do sensor 
  (Espelha a SensorLocalizacaoEntity do Java)
 */
export interface SensorLocalizacao {
    id: string
    localizacao: string
    dataInicio: string // LocalDateTime vira string ISO no JSON
    dataFim?: string   // Opcional, pois a localização atual tem dataFim NULL
}

/**
 Modelo Principal do Sensor
 */
export interface SensorModel {
    id: string
    nome: string
    localizacao: string // Localização atual simplificada
    tipo: TipoSensor
    ativo: boolean
    
    // Novas propriedades para suportar os UseCases de Dashboard e Histórico
    historico?: SensorLocalizacao[]
    leituras?: Leitura[]
}

/*
Por que essa mudança é necessária agora?

1. Consistência com o SensorController: Quando você chama o método listarComLeituras(), o Java envia um SensorComLeiturasDTO. Com essa interface atualizada, você pode usar o mesmo SensorModel para receber esses dados.

2. Preparação para o Dashboard: Agora, no HTML do Dashboard, ela poderá fazer um @for dentro de sensor.historico para mostrar por onde aquele sensor já passou em Sorocaba.

3. Lógica do RegistrarLeituraUseCase: Lembra que o UseCase busca a localização baseada na data? Ter o SensorLocalizacao no modelo permite que o Frontend valide se uma leitura manual está sendo inserida em uma data retroativa coerente.
*/