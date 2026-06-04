import { Header } from "../../components/Header";
import { LoadingSpinner } from "../../components/Loading";
import { EditPacienteDialog, PacienteCard } from "../../components/Paciente";
import { useListaPacientes } from "../../hooks/useListaPacientes";
import { calcularIdade } from "../../utils/dateUtils";

function ListaPacientes() {
  const {
    pacientes,
    selectedPaciente,
    selectedPacienteFotoUrl,
    isEditDialogOpen,
    isLoading,
    isSubmitting,
    setIsEditDialogOpen,
    handleEdit,
    handleUpdatePaciente,
    getFotoUrl,
  } = useListaPacientes();

  return (
    <main className="min-h-screen px-10 pb-12 pt-[284px] text-white">
      <Header />

      {isLoading ? (
        <LoadingSpinner label="Carregando pacientes" />
      ) : (
        pacientes.length > 0 && (
          <section className="flex w-full flex-col gap-6">
            {pacientes.map((paciente) => (
              <PacienteCard
                key={paciente.id}
                paciente={paciente}
                fotoUrl={getFotoUrl(paciente.foto)}
                idade={calcularIdade(paciente.dataNascimento)}
                onEdit={handleEdit}
              />
            ))}
          </section>
        )
      )}

      <EditPacienteDialog
        open={isEditDialogOpen}
        paciente={selectedPaciente}
        fotoUrl={selectedPacienteFotoUrl}
        isSubmitting={isSubmitting}
        onOpenChange={setIsEditDialogOpen}
        onSubmit={handleUpdatePaciente}
      />
    </main>
  );
}

export default ListaPacientes;
