export interface EvolucaoDto {
  id: string
  prontuarioId: string
  evolucao: string
  criadoEm: string
}

export interface CriarEvolucaoDto {
  prontuarioId: string
  evolucao: string
}
