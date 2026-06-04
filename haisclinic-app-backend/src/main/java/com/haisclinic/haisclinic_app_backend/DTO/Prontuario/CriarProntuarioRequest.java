package com.haisclinic.haisclinic_app_backend.DTO.Prontuario;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.StatusEnum;
import jakarta.validation.constraints.NotBlank;

public record CriarProntuarioRequest(
		@NotBlank String pacienteId,
		@NotBlank String profissionalId,
		String queixa,
		String historia,
		StatusEnum status) {
}
