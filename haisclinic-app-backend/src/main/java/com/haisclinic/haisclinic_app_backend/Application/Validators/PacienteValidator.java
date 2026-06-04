package com.haisclinic.haisclinic_app_backend.Application.Validators;

import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteValidator {

	private final PacienteRepository pacienteRepository;

	public void validarEmailDisponivel(String email) {
		if (email == null || email.isBlank()) {
			return;
		}

		pacienteRepository.buscarPorEmail(email)
			.ifPresent(paciente -> {
				throw new AlreadyExistsException("Paciente com este e-mail já existe.");
			});
	}

	public void validarEmailDisponivelParaAtualizacao(String pacienteId, String email) {
		if (email == null || email.isBlank()) {
			return;
		}

		pacienteRepository.buscarPorEmail(email)
			.filter(paciente -> !paciente.getId().equals(pacienteId))
			.ifPresent(paciente -> {
				throw new AlreadyExistsException("Paciente com este e-mail já existe.");
			});
	}
}
