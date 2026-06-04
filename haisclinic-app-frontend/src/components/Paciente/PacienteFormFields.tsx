import { FormInput, FormPhotoInput, FormTextarea } from "../Form";
import type { CriarPacienteDto } from "../../dtos/paciente.dto";

interface PacienteFormFieldsProps {
  form: CriarPacienteDto;
  fotoUrl: string | null;
  onChange: (form: CriarPacienteDto) => void;
}

export function PacienteFormFields({
  form,
  fotoUrl,
  onChange,
}: PacienteFormFieldsProps) {
  return (
    <>
      <FormPhotoInput
        id="editar-paciente-foto"
        file={form.foto}
        initialImageUrl={fotoUrl}
        onChange={(file) => onChange({ ...form, foto: file })}
      />

      <div className="mt-3 space-y-4">
        <FormInput
          label="Nome"
          name="nome"
          value={form.nome}
          onChange={(value) => onChange({ ...form, nome: value })}
        />
        <FormInput
          label="Sobrenome"
          name="sobrenome"
          value={form.sobrenome}
          onChange={(value) => onChange({ ...form, sobrenome: value })}
        />
        <FormInput
          label="Data de Nascimento"
          name="dataNascimento"
          type="date"
          value={form.dataNascimento}
          onChange={(value) => onChange({ ...form, dataNascimento: value })}
        />
        <FormInput
          label="Email"
          name="email"
          type="email"
          value={form.email}
          onChange={(value) => onChange({ ...form, email: value })}
        />
        <FormTextarea
          label="Observações"
          name="observacoes"
          value={form.observacoes}
          onChange={(value) => onChange({ ...form, observacoes: value })}
        />
      </div>
    </>
  );
}
