package com.haisclinic.haisclinic_app_backend.DTO.Profissional;

import jakarta.validation.constraints.Email;

public record AtualizarProfissionalRequest(
		String nome,
		String sobrenome,
		String especialidade,
		@Email String email,
		String foto) {
}
