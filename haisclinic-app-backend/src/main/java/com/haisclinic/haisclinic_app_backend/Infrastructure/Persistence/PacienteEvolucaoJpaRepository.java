package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.PacienteEvolucao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteEvolucaoJpaRepository extends JpaRepository<PacienteEvolucao, String> {

	List<PacienteEvolucao> findByProntuarioId(String prontuarioId);
}
