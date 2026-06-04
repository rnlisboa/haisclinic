package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PacienteRepositoryImpl implements PacienteRepository {

	private final PacienteJpaRepository pacienteJpaRepository;

	@Override
	public Paciente salvar(Paciente paciente) {
		return pacienteJpaRepository.save(paciente);
	}

	@Override
	public Optional<Paciente> buscarPorId(String id) {
		return pacienteJpaRepository.findById(id);
	}

	@Override
	public Optional<Paciente> buscarPorEmail(String email) {
		return pacienteJpaRepository.findByEmail(email);
	}

	@Override
	public List<Paciente> buscarTodos() {
		return pacienteJpaRepository.findAll();
	}

	@Override
	public void deletar(Paciente paciente) {
		pacienteJpaRepository.delete(paciente);
	}
}
