import type { ProntuarioDto } from '../../dtos/prontuario.dto'
import { formatarData } from '../../utils/dateUtils'

interface PacienteTimelineProps {
  evolucoes: NonNullable<ProntuarioDto['evolucoes']>
}

export function PacienteTimeline({ evolucoes }: PacienteTimelineProps) {
  return (
    <ol className="relative space-y-7 before:absolute before:left-[7px] before:top-2 before:h-[calc(100%-16px)] before:w-px before:bg-white">
      {evolucoes.map((evolucao) => (
        <li key={evolucao.id} className="relative flex items-start gap-3 pl-6">
          <span className="absolute left-0 top-1.5 h-4 w-4 rounded-full bg-white" />
          <time className="text-base font-medium">
            {formatarData(evolucao.criadoEm)}
          </time>
          <p className="text-base">{evolucao.evolucao}</p>
        </li>
      ))}
    </ol>
  )
}
