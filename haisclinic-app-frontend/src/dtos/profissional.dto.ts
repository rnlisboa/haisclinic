export interface CriarProfissionalDto {
  nome: string
  sobrenome: string
  especialidade: string
  email: string
  foto?: File | null
}

export interface ProfissionalDto {
  id: string
  nome: string
  sobrenome: string
  especialidade: string
  email: string
  criadoEm: string
  atualizadoEm: string
  foto: string | null
}
