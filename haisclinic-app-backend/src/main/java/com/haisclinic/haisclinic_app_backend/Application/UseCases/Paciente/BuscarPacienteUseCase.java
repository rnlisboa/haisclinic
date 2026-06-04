package com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente;

import com.haisclinic.haisclinic_app_backend.DTO.Paciente.PacienteResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarPacienteUseCase {

	private final PacienteRepository pacienteRepository;
	private final PacienteMapper pacienteMapper;

	public PacienteResponse buscarPorId(String id) {
		return pacienteRepository.buscarPorId(id)
			.map(pacienteMapper::toResponse)
			.orElseThrow(() -> new NotFoundException("Paciente não encontrado."));
	}

	public List<PacienteResponse> buscarTodos() {
		return pacienteRepository.buscarTodos().stream().map(pacienteMapper::toResponse).toList();
	}
}
