package com.haisclinic.haisclinic_app_backend.DTO.Profissional;

import java.time.LocalDateTime;

public record ProfissionalResponse(
		String id,
		String nome,
		String sobrenome,
		String especialidade,
		String email,
		LocalDateTime criadoEm,
		LocalDateTime atualizadoEm,
		String foto) {
}
