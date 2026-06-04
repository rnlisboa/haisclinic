package com.haisclinic.haisclinic_app_backend.Application.UseCases.Prontuario;

import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.ProntuarioResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProntuarioRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.ProntuarioMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarProntuarioUseCase {

	private final ProntuarioRepository prontuarioRepository;
	private final PacienteRepository pacienteRepository;
	private final ProfissionalRepository profissionalRepository;
	private final ProntuarioMapper prontuarioMapper;

	public ProntuarioResponse buscarPorId(String id) {
		return prontuarioRepository.buscarPorId(id)
			.map(this::toResponseComProfissional)
			.orElseThrow(() -> new NotFoundException("Prontuário não encontrado."));
	}

	public List<ProntuarioResponse> buscarTodos() {
		return prontuarioRepository.buscarTodos().stream().map(this::toResponseComProfissional).toList();
	}

	public List<ProntuarioResponse> buscarPorPacienteId(String pacienteId) {
		pacienteRepository.buscarPorId(pacienteId).orElseThrow(() -> new NotFoundException("Paciente não encontrado."));

		return prontuarioRepository.buscarPorPacienteId(pacienteId).stream().map(this::toResponseComProfissional).toList();
	}

	private ProntuarioResponse toResponseComProfissional(Prontuario prontuario) {
		Profissional profissional = profissionalRepository.buscarPorId(prontuario.getProfissionalId()).orElse(null);
		return prontuarioMapper.toResponse(prontuario, profissional);
	}
}
