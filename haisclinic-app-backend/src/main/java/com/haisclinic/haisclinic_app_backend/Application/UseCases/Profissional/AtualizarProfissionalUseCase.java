package com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional;

import com.haisclinic.haisclinic_app_backend.Application.Validators.ProfissionalValidator;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.AtualizarProfissionalRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.ProfissionalResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.ProfissionalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarProfissionalUseCase {

	private final ProfissionalRepository profissionalRepository;
	private final ProfissionalMapper profissionalMapper;
	private final ProfissionalValidator profissionalValidator;

	public ProfissionalResponse execute(String id, AtualizarProfissionalRequest request) {
		Profissional profissional = profissionalRepository.buscarPorId(id)
			.orElseThrow(() -> new NotFoundException("Profissional não encontrado."));

		profissionalValidator.validarEmailDisponivelParaAtualizacao(id, request.email());
		profissionalMapper.updateDomain(profissional, request);

		return profissionalMapper.toResponse(profissionalRepository.salvar(profissional));
	}
}
