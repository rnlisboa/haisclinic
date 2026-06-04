package com.haisclinic.haisclinic_app_backend.Domain.Repository;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import java.util.List;
import java.util.Optional;

public interface ProntuarioRepository {

	Prontuario salvar(Prontuario prontuario);

	Optional<Prontuario> buscarPorId(String id);

	List<Prontuario> buscarTodos();

	List<Prontuario> buscarPorPacienteId(String pacienteId);
}
