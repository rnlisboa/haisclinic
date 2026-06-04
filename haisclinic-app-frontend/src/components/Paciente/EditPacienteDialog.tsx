import { type FormEvent, useEffect, useState } from "react";
import { Button } from "../Button";
import { FormDialog } from "../Dialog";
import type { CriarPacienteDto, PacienteDto } from "../../dtos/paciente.dto";
import { PacienteFormFields } from "./PacienteFormFields";

interface EditPacienteDialogProps {
  open: boolean;
  paciente: PacienteDto | null;
  fotoUrl: string | null;
  isSubmitting: boolean;
  onOpenChange: (open: boolean) => void;
  onSubmit: (id: string, data: CriarPacienteDto) => Promise<void>;
}

const initialForm: CriarPacienteDto = {
  nome: "",
  sobrenome: "",
  dataNascimento: "",
  email: "",
  observacoes: "",
  foto: null,
};

export function EditPacienteDialog({
  open,
  paciente,
  fotoUrl,
  isSubmitting,
  onOpenChange,
  onSubmit,
}: EditPacienteDialogProps) {
  const [form, setForm] = useState<CriarPacienteDto>(initialForm);

  useEffect(() => {
    if (!paciente) {
      setForm(initialForm);
      return;
    }

    setForm({
      nome: paciente.nome,
      sobrenome: paciente.sobrenome,
      dataNascimento: paciente.dataNascimento,
      email: paciente.email ?? "",
      observacoes: paciente.observacoes ?? "",
      foto: null,
    });
  }, [paciente]);

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    if (!paciente) {
      return;
    }

    await onSubmit(paciente.id, form);
  }

  return (
    <FormDialog open={open} title="Editar paciente" onOpenChange={onOpenChange}>
      <form onSubmit={handleSubmit} className="mt-8 flex flex-col">
        <PacienteFormFields form={form} fotoUrl={fotoUrl} onChange={setForm} />

        <Button
          type="submit"
          disabled={isSubmitting}
          className="mt-8 h-14 w-full text-lg font-extrabold text-white focus:ring-white focus:ring-offset-secondary"
        >
          {isSubmitting ? "Salvando" : "Salvar"}
        </Button>
      </form>
    </FormDialog>
  );
}
