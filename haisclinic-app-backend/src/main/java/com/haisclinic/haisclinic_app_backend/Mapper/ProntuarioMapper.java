package com.haisclinic.haisclinic_app_backend.Mapper;

import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.CriarProntuarioRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.ProfissionalResumoResponse;
import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.ProntuarioResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import org.springframework.stereotype.Component;

@Component
public class ProntuarioMapper {

	public Prontuario toDomain(CriarProntuarioRequest request) {
		return Prontuario.criar(request.pacienteId(), request.profissionalId(), request.queixa(), request.historia(),
				request.status());
	}

	public ProntuarioResponse toResponse(Prontuario prontuario) {
		return toResponse(prontuario, null);
	}

	public ProntuarioResponse toResponse(Prontuario prontuario, Profissional profissional) {
		return new ProntuarioResponse(prontuario.getId(), prontuario.getPacienteId(), prontuario.getProfissionalId(),
				prontuario.getQueixa(), prontuario.getHistoria(), prontuario.getCriadoEm(),
				prontuario.getAtualizadoEm(), prontuario.getStatus(), profissionalResumo(profissional));
	}

	private ProfissionalResumoResponse profissionalResumo(Profissional profissional) {
		if (profissional == null) {
			return null;
		}

		return new ProfissionalResumoResponse(profissional.getId(), profissional.getNome(), profissional.getSobrenome(),
				profissional.getEspecialidade());
	}
}
