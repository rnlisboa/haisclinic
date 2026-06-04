package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProntuarioRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProntuarioRepositoryImpl implements ProntuarioRepository {

	private final ProntuarioJpaRepository prontuarioJpaRepository;

	@Override
	public Prontuario salvar(Prontuario prontuario) {
		return prontuarioJpaRepository.save(prontuario);
	}

	@Override
	public Optional<Prontuario> buscarPorId(String id) {
		return prontuarioJpaRepository.findById(id);
	}

	@Override
	public List<Prontuario> buscarTodos() {
		return prontuarioJpaRepository.findAll();
	}

	@Override
	public List<Prontuario> buscarPorPacienteId(String pacienteId) {
		return prontuarioJpaRepository.findByPacienteId(pacienteId);
	}
}
