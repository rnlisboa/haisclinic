import { Button } from "../../components/Button";
import {
  FormContainer,
  FormInput,
  FormPhotoInput,
  FormTextarea,
  FormTypeSelector,
} from "../../components/Form";
import { Header } from "../../components/Header";
import { cadastroOptions, useRegister } from "../../hooks/useRegister";

function Register() {
  const {
    cadastroTipo,
    paciente,
    profissional,
    foto,
    isSubmitting,
    setCadastroTipo,
    setPaciente,
    setProfissional,
    handleFotoChange,
    handleSubmit,
  } = useRegister();

  return (
    <main className="min-h-screen px-6 pb-12 pt-[216px] text-white">
      <Header />

      <section className="mx-auto flex h-[800px] w-full max-w-[800px] flex-col rounded bg-secondary px-12 py-8 shadow-xl">
        <FormTypeSelector
          value={cadastroTipo}
          options={cadastroOptions}
          onChange={setCadastroTipo}
        />

        <FormContainer onSubmit={handleSubmit}>
          <FormPhotoInput
            id={`${cadastroTipo}-foto`}
            file={foto}
            onChange={handleFotoChange}
          />

          {cadastroTipo === "paciente" ? (
            <div className="mt-3 space-y-4">
              <FormInput
                label="Nome"
                name="nome"
                value={paciente.nome}
                onChange={(value) =>
                  setPaciente((current) => ({ ...current, nome: value }))
                }
              />
              <FormInput
                label="Sobrenome"
                name="sobrenome"
                value={paciente.sobrenome}
                onChange={(value) =>
                  setPaciente((current) => ({ ...current, sobrenome: value }))
                }
              />
              <FormInput
                label="Data de Nascimento"
                name="dataNascimento"
                type="date"
                value={paciente.dataNascimento}
                onChange={(value) =>
                  setPaciente((current) => ({
                    ...current,
                    dataNascimento: value,
                  }))
                }
              />
              <FormInput
                label="Email"
                name="email"
                type="email"
                value={paciente.email}
                onChange={(value) =>
                  setPaciente((current) => ({ ...current, email: value }))
                }
              />
              <FormTextarea
                label="Observações"
                name="observacoes"
                value={paciente.observacoes}
                onChange={(value) =>
                  setPaciente((current) => ({
                    ...current,
                    observacoes: value,
                  }))
                }
              />
            </div>
          ) : (
            <div className="mt-3 space-y-4">
              <FormInput
                label="Nome"
                name="nome"
                value={profissional.nome}
                onChange={(value) =>
                  setProfissional((current) => ({ ...current, nome: value }))
                }
              />
              <FormInput
                label="Sobrenome"
                name="sobrenome"
                value={profissional.sobrenome}
                onChange={(value) =>
                  setProfissional((current) => ({
                    ...current,
                    sobrenome: value,
                  }))
                }
              />
              <FormInput
                label="Especialidade"
                name="especialidade"
                value={profissional.especialidade}
                onChange={(value) =>
                  setProfissional((current) => ({
                    ...current,
                    especialidade: value,
                  }))
                }
              />
              <FormInput
                label="Email"
                name="email"
                type="email"
                value={profissional.email}
                onChange={(value) =>
                  setProfissional((current) => ({ ...current, email: value }))
                }
              />
            </div>
          )}

          <Button
            type="submit"
            disabled={isSubmitting}
            className="mt-auto h-14 w-full text-lg font-extrabold text-white focus:ring-white focus:ring-offset-secondary"
          >
            {isSubmitting ? "Cadastrando" : "Cadastrar"}
          </Button>
        </FormContainer>
      </section>
    </main>
  );
}

export default Register;
