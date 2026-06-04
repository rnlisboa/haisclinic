package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Prontuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioJpaRepository extends JpaRepository<Prontuario, String> {

	List<Prontuario> findByPacienteId(String pacienteId);
}
