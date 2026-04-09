export enum TipoSensor{
    SOLO = 'SOLO',
    CLIMA = 'CLIMA',
    NIVEL_TANQUE = 'NIVEL_TANQUE',
    PH = 'PH'
}
export interface SensorModel {
    id: string
    nome: string
    localizacao: string
    tipo: TipoSensor
    ativo: boolean
}
