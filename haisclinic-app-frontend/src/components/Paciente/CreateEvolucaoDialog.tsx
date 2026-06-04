import { type FormEvent, useEffect, useState } from 'react'
import { Button } from '../Button'
import { FormDialog } from '../Dialog'
import { FormTextarea } from '../Form'
import type { CriarEvolucaoDto } from '../../dtos/evolucao.dto'

interface CreateEvolucaoDialogProps {
  open: boolean
  prontuarioId: string | null
  isSubmitting: boolean
  onOpenChange: (open: boolean) => void
  onSubmit: (data: CriarEvolucaoDto) => Promise<void>
}

export function CreateEvolucaoDialog({
  open,
  prontuarioId,
  isSubmitting,
  onOpenChange,
  onSubmit,
}: CreateEvolucaoDialogProps) {
  const [evolucao, setEvolucao] = useState('')

  useEffect(() => {
    if (!open) {
      setEvolucao('')
    }
  }, [open])

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()

    if (!prontuarioId) {
      return
    }

    await onSubmit({
      prontuarioId,
      evolucao,
    })
  }

  return (
    <FormDialog open={open} title="Nova evolução" onOpenChange={onOpenChange}>
      <form onSubmit={handleSubmit} className="mt-8 flex flex-col">
        <FormTextarea
          label="Evolução"
          name="evolucao"
          value={evolucao}
          onChange={setEvolucao}
        />

        <Button
          type="submit"
          disabled={isSubmitting || !evolucao.trim()}
          className="mt-8 h-14 w-full text-lg font-extrabold text-white focus:ring-white focus:ring-offset-secondary"
        >
          {isSubmitting ? 'Salvando' : 'Salvar'}
        </Button>
      </form>
    </FormDialog>
  )
}
