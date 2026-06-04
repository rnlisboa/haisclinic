import { FiEdit3, FiEye, FiUser } from "react-icons/fi";
import { Link } from "react-router-dom";
import type { PacienteDto } from "../../dtos/paciente.dto";

interface PacienteCardProps {
  paciente: PacienteDto;
  fotoUrl: string | null;
  idade: number;
  onEdit: (paciente: PacienteDto) => void;
}

export function PacienteCard({
  paciente,
  fotoUrl,
  idade,
  onEdit,
}: PacienteCardProps) {
  const nomeCompleto = `${paciente.nome} ${paciente.sobrenome}`;

  return (
    <article className="flex h-32 w-full max-w-[888px] items-center rounded bg-secondary px-8 text-white shadow-lg">
      <div className="flex h-16 w-16 shrink-0 items-center justify-center overflow-hidden rounded-full bg-white text-primary">
        {fotoUrl ? (
          <img
            src={fotoUrl}
            alt={nomeCompleto}
            className="h-full w-full object-cover"
          />
        ) : (
          <FiUser size={34} aria-hidden />
        )}
      </div>

      <div className="ml-6 min-w-0 flex-1">
        <h2 className="truncate text-lg font-extrabold">
          {nomeCompleto}, {idade} anos
        </h2>
        <p className="mt-4 line-clamp-2 text-lg font-extrabold">
          {paciente.observacoes}
        </p>
      </div>

      <div className="ml-8 flex items-center gap-7">
        <Link
          to={`/pacientes/${paciente.id}`}
          aria-label={`Visualizar ${nomeCompleto}`}
          className="text-white transition hover:text-button"
        >
          <FiEye size={35} aria-hidden />
        </Link>
        <button
          type="button"
          onClick={() => onEdit(paciente)}
          aria-label={`Editar ${nomeCompleto}`}
          className="text-white transition hover:text-button"
        >
          <FiEdit3 size={35} aria-hidden />
        </button>
      </div>
    </article>
  );
}
