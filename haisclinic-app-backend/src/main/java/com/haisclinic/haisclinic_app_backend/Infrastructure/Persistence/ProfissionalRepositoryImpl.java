package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.ProfissionalRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfissionalRepositoryImpl implements ProfissionalRepository {

	private final ProfissionalJpaRepository profissionalJpaRepository;

	@Override
	public Profissional salvar(Profissional profissional) {
		return profissionalJpaRepository.save(profissional);
	}

	@Override
	public Optional<Profissional> buscarPorId(String id) {
		return profissionalJpaRepository.findById(id);
	}

	@Override
	public Optional<Profissional> buscarPorEmail(String email) {
		return profissionalJpaRepository.findByEmail(email);
	}

	@Override
	public List<Profissional> buscarTodos() {
		return profissionalJpaRepository.findAll();
	}

	@Override
	public void deletar(Profissional profissional) {
		profissionalJpaRepository.delete(profissional);
	}
}
