package com.haisclinic.haisclinic_app_backend.Domain.Repository;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.PacienteEvolucao;
import java.util.List;
import java.util.Optional;

public interface PacienteEvolucaoRepository {

	PacienteEvolucao salvar(PacienteEvolucao pacienteEvolucao);

	Optional<PacienteEvolucao> buscarPorId(String id);

	List<PacienteEvolucao> buscarTodos();

	List<PacienteEvolucao> buscarPorProntuarioId(String prontuarioId);
}
