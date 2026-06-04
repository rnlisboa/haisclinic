package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.PacienteEvolucao;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteEvolucaoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PacienteEvolucaoRepositoryImpl implements PacienteEvolucaoRepository {

	private final PacienteEvolucaoJpaRepository pacienteEvolucaoJpaRepository;

	@Override
	public PacienteEvolucao salvar(PacienteEvolucao pacienteEvolucao) {
		return pacienteEvolucaoJpaRepository.save(pacienteEvolucao);
	}

	@Override
	public Optional<PacienteEvolucao> buscarPorId(String id) {
		return pacienteEvolucaoJpaRepository.findById(id);
	}

	@Override
	public List<PacienteEvolucao> buscarTodos() {
		return pacienteEvolucaoJpaRepository.findAll();
	}

	@Override
	public List<PacienteEvolucao> buscarPorProntuarioId(String prontuarioId) {
		return pacienteEvolucaoJpaRepository.findByProntuarioId(prontuarioId);
	}
}
