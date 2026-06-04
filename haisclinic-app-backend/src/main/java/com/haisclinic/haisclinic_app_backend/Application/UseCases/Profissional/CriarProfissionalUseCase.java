package com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional;

import com.haisclinic.haisclinic_app_backend.Application.Validators.ProfissionalValidator;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.CriarProfissionalRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.ProfissionalResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Mapper.ProfissionalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProfissionalUseCase {

	private final ProfissionalRepository profissionalRepository;
	private final ProfissionalMapper profissionalMapper;
	private final ProfissionalValidator profissionalValidator;

	public ProfissionalResponse execute(CriarProfissionalRequest request) {
		profissionalValidator.validarEmailDisponivel(request.email());
		Profissional profissional = profissionalMapper.toDomain(request);
		return profissionalMapper.toResponse(profissionalRepository.salvar(profissional));
	}
}
