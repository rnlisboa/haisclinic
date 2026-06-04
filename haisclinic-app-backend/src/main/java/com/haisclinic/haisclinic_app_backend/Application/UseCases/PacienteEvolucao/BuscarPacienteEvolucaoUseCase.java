package com.haisclinic.haisclinic_app_backend.Application.UseCases.PacienteEvolucao;

import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.PacienteEvolucaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteEvolucaoRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProntuarioRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteEvolucaoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarPacienteEvolucaoUseCase {

	private final PacienteEvolucaoRepository pacienteEvolucaoRepository;
	private final ProntuarioRepository prontuarioRepository;
	private final PacienteEvolucaoMapper pacienteEvolucaoMapper;

	public PacienteEvolucaoResponse buscarPorId(String id) {
		return pacienteEvolucaoRepository.buscarPorId(id)
			.map(pacienteEvolucaoMapper::toResponse)
			.orElseThrow(() -> new NotFoundException("Evolução do paciente não encontrada."));
	}

	public List<PacienteEvolucaoResponse> buscarTodos() {
		return pacienteEvolucaoRepository.buscarTodos().stream().map(pacienteEvolucaoMapper::toResponse).toList();
	}

	public List<PacienteEvolucaoResponse> buscarPorProntuarioId(String prontuarioId) {
		prontuarioRepository.buscarPorId(prontuarioId)
			.orElseThrow(() -> new NotFoundException("Prontuário não encontrado."));

		return pacienteEvolucaoRepository.buscarPorProntuarioId(prontuarioId)
			.stream()
			.map(pacienteEvolucaoMapper::toResponse)
			.toList();
	}
}
