package com.haisclinic.haisclinic_app_backend.Application.Validators;

import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfissionalValidator {

	private final ProfissionalRepository profissionalRepository;

	public void validarEmailDisponivel(String email) {
		if (email == null || email.isBlank()) {
			return;
		}

		profissionalRepository.buscarPorEmail(email)
			.ifPresent(profissional -> {
				throw new AlreadyExistsException("Profissional com este e-mail já existe.");
			});
	}

	public void validarEmailDisponivelParaAtualizacao(String profissionalId, String email) {
		if (email == null || email.isBlank()) {
			return;
		}

		profissionalRepository.buscarPorEmail(email)
			.filter(profissional -> !profissional.getId().equals(profissionalId))
			.ifPresent(profissional -> {
				throw new AlreadyExistsException("Profissional com este e-mail já existe.");
			});
	}
}
