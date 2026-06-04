package com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao;

import jakarta.validation.constraints.NotBlank;

public record CriarPacienteEvolucaoRequest(
		@NotBlank String prontuarioId,
		@NotBlank String evolucao) {
}
