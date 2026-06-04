package com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional;

import com.haisclinic.haisclinic_app_backend.DTO.Profissional.ProfissionalResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.ProfissionalMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarProfissionalUseCase {

	private final ProfissionalRepository profissionalRepository;
	private final ProfissionalMapper profissionalMapper;

	public ProfissionalResponse buscarPorId(String id) {
		return profissionalRepository.buscarPorId(id)
			.map(profissionalMapper::toResponse)
			.orElseThrow(() -> new NotFoundException("Profissional não encontrado."));
	}

	public List<ProfissionalResponse> buscarTodos() {
		return profissionalRepository.buscarTodos().stream().map(profissionalMapper::toResponse).toList();
	}
}
