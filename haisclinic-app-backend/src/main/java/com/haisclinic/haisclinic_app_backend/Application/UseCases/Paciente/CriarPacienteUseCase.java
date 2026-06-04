package com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente;

import com.haisclinic.haisclinic_app_backend.Application.Validators.PacienteValidator;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.CriarPacienteRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.PacienteResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarPacienteUseCase {

	private final PacienteRepository pacienteRepository;
	private final PacienteMapper pacienteMapper;
	private final PacienteValidator pacienteValidator;

	public PacienteResponse execute(CriarPacienteRequest request) {
		pacienteValidator.validarEmailDisponivel(request.email());
		Paciente paciente = pacienteMapper.toDomain(request);
		return pacienteMapper.toResponse(pacienteRepository.salvar(paciente));
	}
}
