package com.haisclinic.haisclinic_app_backend.DTO.Prontuario;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.StatusEnum;
import java.time.LocalDateTime;

public record ProntuarioResponse(
		String id,
		String pacienteId,
		String profissionalId,
		String queixa,
		String historia,
		LocalDateTime criadoEm,
		LocalDateTime atualizadoEm,
		StatusEnum status,
		ProfissionalResumoResponse profissional) {
}
