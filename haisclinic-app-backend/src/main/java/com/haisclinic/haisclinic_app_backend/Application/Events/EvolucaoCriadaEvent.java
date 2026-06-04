package com.haisclinic.haisclinic_app_backend.Application.Events;

public record EvolucaoCriadaEvent(
		String evolucaoId,
		String prontuarioId,
		String pacienteId,
		String nomePaciente) {
}
