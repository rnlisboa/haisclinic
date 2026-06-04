package com.haisclinic.haisclinic_app_backend.Mapper;

import com.haisclinic.haisclinic_app_backend.DTO.Paciente.AtualizarPacienteRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.CriarPacienteRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.PacienteResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

	public Paciente toDomain(CriarPacienteRequest request) {
		return Paciente.criar(request.nome(), request.sobrenome(), request.dataNascimento(), request.email(),
				request.observacoes(), request.foto());
	}

	public void updateDomain(Paciente paciente, AtualizarPacienteRequest request) {
		paciente.atualizar(request.nome(), request.sobrenome(), request.dataNascimento(), request.ativo(),
				request.email(), request.observacoes(), request.foto());
	}

	public PacienteResponse toResponse(Paciente paciente) {
		return new PacienteResponse(paciente.getId(), paciente.getNome(), paciente.getSobrenome(),
				paciente.getDataNascimento(), paciente.isAtivo(), paciente.getEmail(), paciente.getObservacoes(),
				paciente.getCriadoEm(), paciente.getAtualizadoEm(), fotoUrl(paciente));
	}

	private String fotoUrl(Paciente paciente) {
		if (paciente.getFoto() == null || paciente.getFoto().isBlank()) {
			return null;
		}

		return "/files/" + paciente.getFoto();
	}
}
