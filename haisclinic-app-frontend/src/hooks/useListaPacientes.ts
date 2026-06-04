import { useCallback, useEffect, useMemo, useState } from 'react'
import { useToast } from '../components/Toast'
import type { CriarPacienteDto, PacienteDto } from '../dtos/paciente.dto'
import PacienteService from '../services/pacienteService'

const pacienteService = new PacienteService()

export function useListaPacientes() {
  const { showToast } = useToast()
  const [pacientes, setPacientes] = useState<PacienteDto[]>([])
  const [selectedPaciente, setSelectedPaciente] = useState<PacienteDto | null>(
    null,
  )
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false)
  const [isLoading, setIsLoading] = useState(true)
  const [isSubmitting, setIsSubmitting] = useState(false)

  const selectedPacienteFotoUrl = useMemo(
    () => pacienteService.getFotoUrl(selectedPaciente?.foto),
    [selectedPaciente],
  )

  const loadPacientes = useCallback(async () => {
    setIsLoading(true)

    try {
      const response = await pacienteService.buscarPacientes()
      setPacientes(response.data)
    } catch {
      showToast({
        title: 'Erro ao carregar pacientes',
        description: 'Tente novamente em alguns instantes.',
        variant: 'error',
      })
    } finally {
      setIsLoading(false)
    }
  }, [showToast])

  useEffect(() => {
    loadPacientes()
  }, [loadPacientes])

  function handleEdit(paciente: PacienteDto) {
    setSelectedPaciente(paciente)
    setIsEditDialogOpen(true)
  }

  async function handleUpdatePaciente(id: string, data: CriarPacienteDto) {
    setIsSubmitting(true)

    try {
      const { foto, ...pacienteData } = data

      await pacienteService.atualizarPaciente(id, {
        ...pacienteData,
        foto: null,
      })

      if (foto) {
        await pacienteService.salvarFotoPaciente(id, foto)
      }

      await loadPacientes()
      setIsEditDialogOpen(false)
      setSelectedPaciente(null)
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

  return {
    pacientes,
    selectedPaciente,
    selectedPacienteFotoUrl,
    isEditDialogOpen,
    isLoading,
    isSubmitting,
    setIsEditDialogOpen,
    handleEdit,
    handleUpdatePaciente,
    getFotoUrl: pacienteService.getFotoUrl.bind(pacienteService),
  }
}
