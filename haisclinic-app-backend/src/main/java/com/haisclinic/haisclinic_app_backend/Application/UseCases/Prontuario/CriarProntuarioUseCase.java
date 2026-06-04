package com.haisclinic.haisclinic_app_backend.Application.UseCases.Prontuario;

import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.CriarProntuarioRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.ProntuarioResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProntuarioRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.ProntuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProntuarioUseCase {

	private final ProntuarioRepository prontuarioRepository;
	private final PacienteRepository pacienteRepository;
	private final ProfissionalRepository profissionalRepository;
	private final ProntuarioMapper prontuarioMapper;

	public ProntuarioResponse execute(CriarProntuarioRequest request) {
		pacienteRepository.buscarPorId(request.pacienteId())
			.orElseThrow(() -> new NotFoundException("Paciente não encontrado."));
		profissionalRepository.buscarPorId(request.profissionalId())
			.orElseThrow(() -> new NotFoundException("Profissional não encontrado."));

		Prontuario prontuario = prontuarioMapper.toDomain(request);
		return prontuarioMapper.toResponse(prontuarioRepository.salvar(prontuario));
	}
}
