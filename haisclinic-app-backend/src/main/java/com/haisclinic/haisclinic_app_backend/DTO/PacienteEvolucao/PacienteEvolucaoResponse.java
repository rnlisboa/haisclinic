package com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao;

import java.time.LocalDateTime;

public record PacienteEvolucaoResponse(
		String id,
		String prontuarioId,
		String evolucao,
		LocalDateTime criadoEm) {
}
