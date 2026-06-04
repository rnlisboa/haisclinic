package com.haisclinic.haisclinic_app_backend.DTO.Paciente;

import jakarta.validation.constraints.Email;
import java.time.LocalDate;

public record AtualizarPacienteRequest(
		String nome,
		String sobrenome,
		LocalDate dataNascimento,
		Boolean ativo,
		@Email String email,
		String observacoes,
		String foto) {
}
