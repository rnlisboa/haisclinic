package com.haisclinic.haisclinic_app_backend.DTO.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PacienteResponse(
		String id,
		String nome,
		String sobrenome,
		LocalDate dataNascimento,
		boolean ativo,
		String email,
		String observacoes,
		LocalDateTime criadoEm,
		LocalDateTime atualizadoEm,
		String foto) {
}
