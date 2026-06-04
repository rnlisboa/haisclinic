package com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente;

import com.haisclinic.haisclinic_app_backend.DTO.Paciente.PacienteResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteImagemRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalvarFotoPacienteUseCase {

	private final PacienteRepository pacienteRepository;
	private final PacienteImagemRepository pacienteImagemRepository;
	private final PacienteMapper pacienteMapper;

	public PacienteResponse execute(String pacienteId, String nomeOriginal, String contentType, byte[] conteudo) {
		Paciente paciente = pacienteRepository.buscarPorId(pacienteId)
			.orElseThrow(() -> new NotFoundException("Paciente não encontrado."));

		String nomeArquivo = pacienteImagemRepository.salvar(pacienteId, nomeOriginal, contentType, conteudo);
		paciente.setFoto(nomeArquivo);
		return pacienteMapper.toResponse(pacienteRepository.salvar(paciente));
	}
}
