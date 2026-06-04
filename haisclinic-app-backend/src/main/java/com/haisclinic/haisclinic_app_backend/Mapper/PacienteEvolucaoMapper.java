package com.haisclinic.haisclinic_app_backend.Mapper;

import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.CriarPacienteEvolucaoRequest;
import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.PacienteEvolucaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.PacienteEvolucao;
import org.springframework.stereotype.Component;

@Component
public class PacienteEvolucaoMapper {

	public PacienteEvolucao toDomain(CriarPacienteEvolucaoRequest request) {
		return PacienteEvolucao.criar(request.prontuarioId(), request.evolucao());
	}

	public PacienteEvolucaoResponse toResponse(PacienteEvolucao pacienteEvolucao) {
		return new PacienteEvolucaoResponse(pacienteEvolucao.getId(), pacienteEvolucao.getProntuarioId(),
				pacienteEvolucao.getEvolucao(), pacienteEvolucao.getCriadoEm());
	}
}
