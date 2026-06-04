import type { EvolucaoDto } from './evolucao.dto'

export interface ProfissionalResumoDto {
  id: string
  nome: string
  sobrenome: string
  especialidade: string
}

export interface ProntuarioDto {
  id: string
  pacienteId: string
  profissionalId: string
  queixa: string
  historia: string
  criadoEm: string
  atualizadoEm: string
  status: string
  profissional: ProfissionalResumoDto | null
  evolucoes?: EvolucaoDto[]
}

export interface CriarProntuarioDto {
  pacienteId: string
  profissionalId: string
  queixa: string
  historia: string
  status?: string
}
