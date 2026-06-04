package com.haisclinic.haisclinic_app_backend.DTO.Profissional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarProfissionalRequest(
		@NotBlank String nome,
		@NotBlank String sobrenome,
		@NotBlank String especialidade,
		@Email String email,
		String foto) {
}
