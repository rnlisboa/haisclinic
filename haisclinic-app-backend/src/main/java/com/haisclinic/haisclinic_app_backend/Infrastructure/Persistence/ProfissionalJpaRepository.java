package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Profissional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalJpaRepository extends JpaRepository<Profissional, String> {

	Optional<Profissional> findByEmail(String email);
}
