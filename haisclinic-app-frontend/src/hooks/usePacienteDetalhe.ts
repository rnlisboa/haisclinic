import { useCallback, useEffect, useMemo, useState } from 'react'
import { useToast } from '../components/Toast'
import type { CriarEvolucaoDto } from '../dtos/evolucao.dto'
import type { CriarPacienteDto, PacienteDto } from '../dtos/paciente.dto'
import type { ProfissionalDto } from '../dtos/profissional.dto'
import type { CriarProntuarioDto, ProntuarioDto } from '../dtos/prontuario.dto'
import PacienteService from '../services/pacienteService'
import ProfissionalService from '../services/profissionalService'
import ProntuarioService from '../services/prontuarioService'

const pacienteService = new PacienteService()
const profissionalService = new ProfissionalService()
const prontuarioService = new ProntuarioService()

export function usePacienteDetalhe(id?: string) {
  const { showToast } = useToast()
  const [paciente, setPaciente] = useState<PacienteDto | null>(null)
  const [prontuarios, setProntuarios] = useState<ProntuarioDto[]>([])
  const [profissionais, setProfissionais] = useState<ProfissionalDto[]>([])
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false)
  const [isProntuarioDialogOpen, setIsProntuarioDialogOpen] = useState(false)
  const [isEvolucaoDialogOpen, setIsEvolucaoDialogOpen] = useState(false)
  const [selectedProntuarioId, setSelectedProntuarioId] = useState<string | null>(
    null,
  )
  const [isLoading, setIsLoading] = useState(true)
  const [isSubmitting, setIsSubmitting] = useState(false)

  const fotoUrl = useMemo(
    () => pacienteService.getFotoUrl(paciente?.foto),
    [paciente],
  )

  const loadPacienteDetalhe = useCallback(async () => {
    if (!id) {
      setIsLoading(false)
      return
    }

    setIsLoading(true)

    try {
      const [pacienteResponse, prontuariosResponse, profissionaisResponse] =
        await Promise.all([
          pacienteService.buscarPacientePorId(id),
          prontuarioService.buscarProntuariosPorPacienteId(id),
          profissionalService.buscarProfissionais(),
        ])

      const prontuariosComEvolucoes = await Promise.all(
        prontuariosResponse.data.map(async (prontuario) => {
          const evolucoesResponse =
            await prontuarioService.buscarEvolucoesPorProntuarioId(prontuario.id)

          return {
            ...prontuario,
            evolucoes: evolucoesResponse.data,
          }
        }),
      )

      setPaciente(pacienteResponse.data)
      setProntuarios(prontuariosComEvolucoes)
      setProfissionais(profissionaisResponse.data)
    } catch {
      setPaciente(null)
      setProntuarios([])
      setProfissionais([])
      showToast({
        title: 'Erro ao carregar paciente',
        description: 'Não foi possível carregar os dados do paciente.',
        variant: 'error',
      })
    } finally {
      setIsLoading(false)
    }
  }, [id, showToast])

  useEffect(() => {
    loadPacienteDetalhe()
  }, [loadPacienteDetalhe])

  function handleEditarPaciente() {
    setIsEditDialogOpen(true)
  }

  function handleAdicionarProntuario() {
    setIsProntuarioDialogOpen(true)
  }

  function handleNovaEvolucao(prontuarioId: string) {
    setSelectedProntuarioId(prontuarioId)
    setIsEvolucaoDialogOpen(true)
  }

  async function handleUpdatePaciente(pacienteId: string, data: CriarPacienteDto) {
    setIsSubmitting(true)

    try {
      const { foto, ...pacienteData } = data
      const response = await pacienteService.atualizarPaciente(pacienteId, {
        ...pacienteData,
        foto: null,
      })

      if (foto) {
        const fotoResponse = await pacienteService.salvarFotoPaciente(pacienteId, foto)
        setPaciente(fotoResponse.data)
      } else {
        setPaciente(response.data)
      }

      setIsEditDialogOpen(false)
      showToast({
        title: 'Paciente atualizado',
        description: 'As informações foram salvas com sucesso.',
        variant: 'success',
      })
    } catch {
      showToast({
        title: 'Erro ao atualizar paciente',
        description: 'Verifique os dados e tente novamente.',
        variant: 'error',
      })
    } finally {
      setIsSubmitting(false)
    }
  }

  async function handleCreateProntuario(data: CriarProntuarioDto) {
    setIsSubmitting(true)

    try {
      await prontuarioService.criarProntuario(data)
      await loadPacienteDetalhe()
      setIsProntuarioDialogOpen(false)
      showToast({
        title: 'Prontuário criado',
        description: 'O prontuário foi adicionado ao paciente.',
        variant: 'success',
      })
    } catch {
      showToast({
        title: 'Erro ao criar prontuário',
        description: 'Verifique os dados e tente novamente.',
        variant: 'error',
      })
    } finally {
      setIsSubmitting(false)
    }
  }

  async function handleCreateEvolucao(data: CriarEvolucaoDto) {
    setIsSubmitting(true)

    try {
      const response = await prontuarioService.criarEvolucao(data)
      setProntuarios((current) =>
        current.map((prontuario) => {
          if (prontuario.id !== data.prontuarioId) {
            return prontuario
          }

          return {
            ...prontuario,
            evolucoes: [...(prontuario.evolucoes ?? []), response.data],
          }
        }),
      )
      setIsEvolucaoDialogOpen(false)
      setSelectedProntuarioId(null)
      showToast({
        title: 'Evolução criada',
        description: 'A nova evolução foi adicionada à timeline.',
        variant: 'success',
      })
    } catch {
      showToast({
        title: 'Erro ao criar evolução',
        description: 'Verifique os dados e tente novamente.',
        variant: 'error',
      })
    } finally {
      setIsSubmitting(false)
    }
  }

  return {
    paciente,
    prontuarios,
    profissionais,
    fotoUrl,
    isEditDialogOpen,
    isProntuarioDialogOpen,
    isEvolucaoDialogOpen,
    selectedProntuarioId,
    isLoading,
    isSubmitting,
    setIsEditDialogOpen,
    setIsProntuarioDialogOpen,
    setIsEvolucaoDialogOpen,
    handleEditarPaciente,
    handleAdicionarProntuario,
    handleNovaEvolucao,
    handleUpdatePaciente,
    handleCreateProntuario,
    handleCreateEvolucao,
  }
}
