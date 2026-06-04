package com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional;

import com.haisclinic.haisclinic_app_backend.DTO.Profissional.ProfissionalResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalImagemRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.ProfissionalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalvarFotoProfissionalUseCase {

	private final ProfissionalRepository profissionalRepository;
	private final ProfissionalImagemRepository profissionalImagemRepository;
	private final ProfissionalMapper profissionalMapper;

	public ProfissionalResponse execute(String profissionalId, String nomeOriginal, String contentType, byte[] conteudo) {
		Profissional profissional = profissionalRepository.buscarPorId(profissionalId)
			.orElseThrow(() -> new NotFoundException("Profissional não encontrado."));

		String nomeArquivo = profissionalImagemRepository.salvar(profissionalId, nomeOriginal, contentType, conteudo);
		profissional.setFoto(nomeArquivo);
		return profissionalMapper.toResponse(profissionalRepository.salvar(profissional));
	}
}
