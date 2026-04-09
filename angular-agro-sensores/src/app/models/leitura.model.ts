import { SensorModel } from "./sensor.model"

export interface LeituraModel {
    id: number 
    sensor: SensorModel
    valor: number
    dataHora: string
    localizacao: string
}
