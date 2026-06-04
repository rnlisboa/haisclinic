export interface CriarPacienteDto {
  nome: string
  sobrenome: string
  dataNascimento: string
  email: string
  observacoes: string
  foto?: File | null
}

export interface PacienteDto {
  id: string
  nome: string
  sobrenome: string
  dataNascimento: string
  ativo: boolean
  email: string
  observacoes: string
  criadoEm: string
  atualizadoEm: string
  foto: string | null
}
