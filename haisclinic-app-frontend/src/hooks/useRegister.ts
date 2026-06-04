import { type FormEvent, useState } from 'react'
import { useToast } from '../components/Toast'
import type { CriarPacienteDto } from '../dtos/paciente.dto'
import type { CriarProfissionalDto } from '../dtos/profissional.dto'
import PacienteService from '../pages/register/service/pacienteService'
import ProfissionalService from '../pages/register/service/profissionalService'

export type CadastroTipo = 'paciente' | 'profissional'

export const cadastroOptions: Array<{ label: string; value: CadastroTipo }> = [
  { label: 'Paciente', value: 'paciente' },
  { label: 'Profissional', value: 'profissional' },
]

const initialPaciente: CriarPacienteDto = {
  nome: '',
  sobrenome: '',
  dataNascimento: '',
  email: '',
  observacoes: '',
  foto: null,
}

const initialProfissional: CriarProfissionalDto = {
  nome: '',
  sobrenome: '',
  especialidade: '',
  email: '',
  foto: null,
}

const pacienteService = new PacienteService()
const profissionalService = new ProfissionalService()

export function useRegister() {
  const { showToast } = useToast()
  const [cadastroTipo, setCadastroTipo] = useState<CadastroTipo>('paciente')
  const [paciente, setPaciente] = useState<CriarPacienteDto>(initialPaciente)
  const [profissional, setProfissional] =
    useState<CriarProfissionalDto>(initialProfissional)
  const [isSubmitting, setIsSubmitting] = useState(false)

  const foto = cadastroTipo === 'paciente' ? paciente.foto : profissional.foto

  function handleFotoChange(file: File | null) {
    if (cadastroTipo === 'paciente') {
      setPaciente((current) => ({ ...current, foto: file }))
      return
    }

    setProfissional((current) => ({ ...current, foto: file }))
  }

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setIsSubmitting(true)

    try {
      if (cadastroTipo === 'paciente') {
        const { foto: pacienteFoto, ...pacienteData } = paciente
        const response = await pacienteService.criarPaciente({
          ...pacienteData,
          foto: null,
        })

        if (pacienteFoto) {
          await pacienteService.salvarFotoPaciente(response.data.id, pacienteFoto)
        }

        setPaciente(initialPaciente)
        showToast({
          title: 'Paciente cadastrado',
          description: 'O paciente foi salvo com sucesso.',
          variant: 'success',
        })
        return
      }

      const { foto: profissionalFoto, ...profissionalData } = profissional
      const response = await profissionalService.criarProfissional({
        ...profissionalData,
        foto: null,
      })

      if (profissionalFoto) {
        await profissionalService.salvarFotoProfissional(
          response.data.id,
          profissionalFoto,
        )
      }

      setProfissional(initialProfissional)
      showToast({
        title: 'Profissional cadastrado',
        description: 'O profissional foi salvo com sucesso.',
        variant: 'success',
      })
    } catch {
      showToast({
        title: 'Erro ao cadastrar',
        description: 'Não foi possível concluir o cadastro.',
        variant: 'error',
      })
    } finally {
      setIsSubmitting(false)
    }
  }

  return {
    cadastroTipo,
    paciente,
    profissional,
    foto,
    isSubmitting,
    setCadastroTipo,
    setPaciente,
    setProfissional,
    handleFotoChange,
    handleSubmit,
  }
}
