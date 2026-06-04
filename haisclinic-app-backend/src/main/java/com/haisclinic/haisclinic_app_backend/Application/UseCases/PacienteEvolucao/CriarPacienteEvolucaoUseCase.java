package com.haisclinic.haisclinic_app_backend.Application.UseCases.PacienteEvolucao;

import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.CriarPacienteEvolucaoRequest;
import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.PacienteEvolucaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.PacienteEvolucao;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteEvolucaoRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProntuarioRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteEvolucaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarPacienteEvolucaoUseCase {

	private final PacienteEvolucaoRepository pacienteEvolucaoRepository;
	private final ProntuarioRepository prontuarioRepository;
	private final PacienteEvolucaoMapper pacienteEvolucaoMapper;

	public PacienteEvolucaoResponse execute(CriarPacienteEvolucaoRequest request) {
		prontuarioRepository.buscarPorId(request.prontuarioId())
			.orElseThrow(() -> new NotFoundException("Prontuário não encontrado."));

		PacienteEvolucao pacienteEvolucao = pacienteEvolucaoMapper.toDomain(request);
		return pacienteEvolucaoMapper.toResponse(pacienteEvolucaoRepository.salvar(pacienteEvolucao));
	}
}
