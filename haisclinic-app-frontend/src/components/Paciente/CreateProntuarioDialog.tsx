import { type FormEvent, useState } from 'react'
import { Button } from '../Button'
import { FormDialog } from '../Dialog'
import { FormSelect, FormTextarea } from '../Form'
import type { ProfissionalDto } from '../../dtos/profissional.dto'
import type { CriarProntuarioDto } from '../../dtos/prontuario.dto'

interface CreateProntuarioDialogProps {
  open: boolean
  pacienteId: string
  profissionais: ProfissionalDto[]
  isSubmitting: boolean
  onOpenChange: (open: boolean) => void
  onSubmit: (data: CriarProntuarioDto) => Promise<void>
}

const initialForm = {
  profissionalId: '',
  queixa: '',
  historia: '',
  status: 'EM_ATENDIMENTO',
}

export function CreateProntuarioDialog({
  open,
  pacienteId,
  profissionais,
  isSubmitting,
  onOpenChange,
  onSubmit,
}: CreateProntuarioDialogProps) {
  const [form, setForm] = useState(initialForm)

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()

    await onSubmit({
      pacienteId,
      profissionalId: form.profissionalId,
      queixa: form.queixa,
      historia: form.historia,
      status: form.status,
    })

    setForm(initialForm)
  }

  return (
    <FormDialog
      open={open}
      title="Adicionar prontuário"
      onOpenChange={onOpenChange}
    >
      <form onSubmit={handleSubmit} className="mt-8 flex flex-col">
        <div className="space-y-4">
          <FormSelect
            label="Profissional"
            name="profissionalId"
            value={form.profissionalId}
            placeholder="Selecione o profissional"
            options={profissionais.map((profissional) => ({
              value: profissional.id,
              label: `${profissional.nome} ${profissional.sobrenome} - ${profissional.especialidade}`,
            }))}
            onChange={(value) =>
              setForm((current) => ({ ...current, profissionalId: value }))
            }
          />

          <FormTextarea
            label="Queixa"
            name="queixa"
            value={form.queixa}
            onChange={(value) =>
              setForm((current) => ({ ...current, queixa: value }))
            }
          />

          <FormTextarea
            label="História"
            name="historia"
            value={form.historia}
            onChange={(value) =>
              setForm((current) => ({ ...current, historia: value }))
            }
          />

          <FormSelect
            label="Status"
            name="status"
            value={form.status}
            options={[
              { value: 'EM_ATENDIMENTO', label: 'Em atendimento' },
              { value: 'FINALIZADO', label: 'Finalizado' },
            ]}
            onChange={(value) =>
              setForm((current) => ({ ...current, status: value }))
            }
          />
        </div>

        <Button
          type="submit"
          disabled={isSubmitting || !form.profissionalId}
          className="mt-8 h-14 w-full text-lg font-extrabold text-white focus:ring-white focus:ring-offset-secondary"
        >
          {isSubmitting ? 'Salvando' : 'Salvar'}
        </Button>
      </form>
    </FormDialog>
  )
}
