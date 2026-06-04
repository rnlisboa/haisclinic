package com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente;

import com.haisclinic.haisclinic_app_backend.Application.Validators.PacienteValidator;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.AtualizarPacienteRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.PacienteResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarPacienteUseCase {

	private final PacienteRepository pacienteRepository;
	private final PacienteMapper pacienteMapper;
	private final PacienteValidator pacienteValidator;

	public PacienteResponse execute(String id, AtualizarPacienteRequest request) {
		Paciente paciente = pacienteRepository.buscarPorId(id)
			.orElseThrow(() -> new NotFoundException("Paciente não encontrado."));

		pacienteValidator.validarEmailDisponivelParaAtualizacao(id, request.email());
		pacienteMapper.updateDomain(paciente, request);

		return pacienteMapper.toResponse(pacienteRepository.salvar(paciente));
	}
}
