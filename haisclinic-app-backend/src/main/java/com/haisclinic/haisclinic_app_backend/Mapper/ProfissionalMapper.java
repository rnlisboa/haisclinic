package com.haisclinic.haisclinic_app_backend.Mapper;

import com.haisclinic.haisclinic_app_backend.DTO.Profissional.AtualizarProfissionalRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.CriarProfissionalRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.ProfissionalResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {

	public Profissional toDomain(CriarProfissionalRequest request) {
		return Profissional.criar(request.nome(), request.sobrenome(), request.especialidade(), request.email(),
				request.foto());
	}

	public void updateDomain(Profissional profissional, AtualizarProfissionalRequest request) {
		profissional.atualizar(request.nome(), request.sobrenome(), request.especialidade(), request.email(),
				request.foto());
	}

	public ProfissionalResponse toResponse(Profissional profissional) {
		return new ProfissionalResponse(profissional.getId(), profissional.getNome(), profissional.getSobrenome(),
				profissional.getEspecialidade(), profissional.getEmail(), profissional.getCriadoEm(),
				profissional.getAtualizadoEm(), fotoUrl(profissional));
	}

	private String fotoUrl(Profissional profissional) {
		if (profissional.getFoto() == null || profissional.getFoto().isBlank()) {
			return null;
		}

		return "/files/" + profissional.getFoto();
	}
}
