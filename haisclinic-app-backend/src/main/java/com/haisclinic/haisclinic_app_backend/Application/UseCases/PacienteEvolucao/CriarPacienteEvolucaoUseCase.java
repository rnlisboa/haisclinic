package com.haisclinic.haisclinic_app_backend.Application.UseCases.PacienteEvolucao;

import com.haisclinic.haisclinic_app_backend.Application.Events.EvolucaoCriadaEvent;
import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.CriarPacienteEvolucaoRequest;
import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.PacienteEvolucaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.PacienteEvolucao;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteEvolucaoRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProntuarioRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteEvolucaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarPacienteEvolucaoUseCase {

	private final PacienteEvolucaoRepository pacienteEvolucaoRepository;
	private final ProntuarioRepository prontuarioRepository;
	private final PacienteRepository pacienteRepository;
	private final PacienteEvolucaoMapper pacienteEvolucaoMapper;
	private final ApplicationEventPublisher eventPublisher;

	public PacienteEvolucaoResponse execute(CriarPacienteEvolucaoRequest request) {
		Prontuario prontuario = prontuarioRepository.buscarPorId(request.prontuarioId())
			.orElseThrow(() -> new NotFoundException("Prontuário não encontrado."));
		Paciente paciente = pacienteRepository.buscarPorId(prontuario.getPacienteId())
			.orElseThrow(() -> new NotFoundException("Paciente não encontrado."));

		PacienteEvolucao pacienteEvolucao = pacienteEvolucaoMapper.toDomain(request);
		PacienteEvolucao pacienteEvolucaoSalva = pacienteEvolucaoRepository.salvar(pacienteEvolucao);

		eventPublisher.publishEvent(new EvolucaoCriadaEvent(
				pacienteEvolucaoSalva.getId(),
				pacienteEvolucaoSalva.getProntuarioId(),
				prontuario.getPacienteId(),
				nomeCompleto(paciente)));

		return pacienteEvolucaoMapper.toResponse(pacienteEvolucaoSalva);
	}

	private String nomeCompleto(Paciente paciente) {
		return paciente.getNome() + " " + paciente.getSobrenome();
	}
}
