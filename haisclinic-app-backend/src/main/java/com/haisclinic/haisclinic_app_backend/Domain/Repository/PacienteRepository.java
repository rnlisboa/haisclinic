package com.haisclinic.haisclinic_app_backend.Domain.Repository;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import java.util.List;
import java.util.Optional;

public interface PacienteRepository {

	Paciente salvar(Paciente paciente);

	Optional<Paciente> buscarPorId(String id);

	Optional<Paciente> buscarPorEmail(String email);

	List<Paciente> buscarTodos();

	void deletar(Paciente paciente);
}
