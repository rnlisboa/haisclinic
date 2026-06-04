package com.haisclinic.haisclinic_app_backend.Domain.Repository;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import java.util.List;
import java.util.Optional;

public interface ProfissionalRepository {

	Profissional salvar(Profissional profissional);

	Optional<Profissional> buscarPorId(String id);

	Optional<Profissional> buscarPorEmail(String email);

	List<Profissional> buscarTodos();

	void deletar(Profissional profissional);
}
