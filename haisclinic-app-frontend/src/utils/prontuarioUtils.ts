import type { ProntuarioDto } from '../dtos/prontuario.dto'

export function nomeProfissional(prontuario: ProntuarioDto) {
  if (!prontuario.profissional) {
    return 'Profissional'
  }

  return `${prontuario.profissional.nome} ${prontuario.profissional.sobrenome}`
}
