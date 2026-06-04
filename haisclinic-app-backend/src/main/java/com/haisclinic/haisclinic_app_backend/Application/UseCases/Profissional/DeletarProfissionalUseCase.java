package com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarProfissionalUseCase {

	private final ProfissionalRepository profissionalRepository;

	public void execute(String id) {
		Profissional profissional = profissionalRepository.buscarPorId(id)
			.orElseThrow(() -> new NotFoundException("Profissional não encontrado."));

		profissionalRepository.deletar(profissional);
	}
}
