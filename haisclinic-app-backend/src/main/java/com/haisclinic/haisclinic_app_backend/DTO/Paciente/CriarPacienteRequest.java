package com.haisclinic.haisclinic_app_backend.DTO.Paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CriarPacienteRequest(
		@NotBlank String nome,
		@NotBlank String sobrenome,
		@NotNull LocalDate dataNascimento,
		@Email String email,
		String observacoes,
		String foto) {
}
