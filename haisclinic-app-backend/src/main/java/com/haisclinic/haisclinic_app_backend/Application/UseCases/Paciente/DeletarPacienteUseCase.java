package com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarPacienteUseCase {

	private final PacienteRepository pacienteRepository;

	public void execute(String id) {
		Paciente paciente = pacienteRepository.buscarPorId(id)
			.orElseThrow(() -> new NotFoundException("Paciente não encontrado."));

		pacienteRepository.deletar(paciente);
	}
}
