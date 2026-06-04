import { FiEdit3, FiPlus, FiPlusCircle, FiUser } from "react-icons/fi";
import { FaUserDoctor } from "react-icons/fa6";
import { useParams } from "react-router-dom";
import { Header } from "../../components/Header";
import { LoadingSpinner } from "../../components/Loading";
import {
  CreateEvolucaoDialog,
  CreateProntuarioDialog,
  EditPacienteDialog,
  PacienteTimeline,
} from "../../components/Paciente";
import { usePacienteDetalhe } from "../../hooks/usePacienteDetalhe";
import { calcularIdade, formatarData } from "../../utils/dateUtils";
import { nomeProfissional } from "../../utils/prontuarioUtils";

function PacienteDetalhe() {
  const { id } = useParams<{ id: string }>();
  const {
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
  } = usePacienteDetalhe(id);

  if (isLoading) {
    return (
      <main className="min-h-screen px-9 pb-12 pt-[184px] text-white">
        <Header />
        <LoadingSpinner label="Carregando dados do paciente" />
      </main>
    );
  }

  if (!paciente) {
    return (
      <main className="min-h-screen px-9 pb-12 pt-[184px] text-white">
        <Header />
      </main>
    );
  }

  return (
    <main className="min-h-screen px-9 pb-16 pt-[184px] text-white">
      <Header />

      <section className="flex items-center gap-16">
        <div className="flex h-40 w-40 shrink-0 items-center justify-center overflow-hidden rounded-full bg-white text-primary">
          {fotoUrl ? (
            <img
              src={fotoUrl}
              alt={`${paciente.nome} ${paciente.sobrenome}`}
              className="h-full w-full object-cover"
            />
          ) : (
            <FiUser size={84} aria-hidden />
          )}
        </div>

        <div className="flex min-w-0 items-center">
          <div className="flex min-w-[360px] flex-col justify-center">
            <h1 className="text-3xl font-extrabold">
              {paciente.nome} {paciente.sobrenome},{" "}
              {calcularIdade(paciente.dataNascimento)} anos
            </h1>
            <a
              href={`mailto:${paciente.email}`}
              className="mt-3 block text-xl font-medium underline underline-offset-4"
            >
              {paciente.email}
            </a>
            <p className="mt-3 text-xl">
              Cadastrado em {formatarData(paciente.criadoEm)}
            </p>
            <p className="mt-2 text-xl">{paciente.observacoes}</p>
          </div>

          <div className="mx-10 h-36 w-px bg-white/80" aria-hidden />

          <div className="flex flex-col items-start gap-7">
            <button
              type="button"
              onClick={handleEditarPaciente}
              className="inline-flex items-center gap-5 text-2xl transition hover:text-button"
            >
              <FiEdit3 size={46} aria-hidden />
              Editar informações do paciente
            </button>
            <button
              type="button"
              onClick={handleAdicionarProntuario}
              className="inline-flex items-center gap-5 text-2xl transition hover:text-button"
            >
              <span className="flex h-14 w-14 items-center justify-center rounded-full bg-white text-primary">
                <FiPlus size={38} aria-hidden />
              </span>
              Adicionar prontuário
            </button>
          </div>
        </div>
      </section>

      <div className="mt-14 h-px w-full bg-white/90" aria-hidden />

      <section className="mt-16 space-y-16">
        {prontuarios.map((prontuario) => (
          <article key={prontuario.id}>
            <h2 className="text-3xl font-extrabold">
              Prontuário #{prontuario.id.slice(0, 4)}
            </h2>

            <div className="mt-3 flex items-center gap-3 text-lg">
              <FaUserDoctor size={22} aria-hidden />
              <span>{nomeProfissional(prontuario)}</span>
              <span className="h-6 w-px bg-white" aria-hidden />
              <span className="font-semibold">
                {prontuario.profissional?.especialidade ?? "Especialidade"}
              </span>
            </div>

            <dl className="mt-5 space-y-5 text-xl">
              <div className="flex gap-5">
                <dt className="font-extrabold">Queixa</dt>
                <dd>{prontuario.queixa}</dd>
              </div>
              <div className="flex gap-5">
                <dt className="font-extrabold">História</dt>
                <dd>{prontuario.historia}</dd>
              </div>
            </dl>

            <div className="mt-5">
              <PacienteTimeline evolucoes={prontuario.evolucoes ?? []} />
            </div>

            <button
              type="button"
              onClick={() => handleNovaEvolucao(prontuario.id)}
              className="mt-7 inline-flex items-center gap-2 text-lg font-extrabold transition hover:text-button"
            >
              <FiPlusCircle size={20} aria-hidden />
              Nova evolução
            </button>
          </article>
        ))}
      </section>

      <EditPacienteDialog
        open={isEditDialogOpen}
        paciente={paciente}
        fotoUrl={fotoUrl}
        isSubmitting={isSubmitting}
        onOpenChange={setIsEditDialogOpen}
        onSubmit={handleUpdatePaciente}
      />

      <CreateProntuarioDialog
        open={isProntuarioDialogOpen}
        pacienteId={paciente.id}
        profissionais={profissionais}
        isSubmitting={isSubmitting}
        onOpenChange={setIsProntuarioDialogOpen}
        onSubmit={handleCreateProntuario}
      />

      <CreateEvolucaoDialog
        open={isEvolucaoDialogOpen}
        prontuarioId={selectedProntuarioId}
        isSubmitting={isSubmitting}
        onOpenChange={setIsEvolucaoDialogOpen}
        onSubmit={handleCreateEvolucao}
      />
    </main>
  );
}

export default PacienteDetalhe;
