package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Paciente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteJpaRepository extends JpaRepository<Paciente, String> {

	Optional<Paciente> findByEmail(String email);
}
