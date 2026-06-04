package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoJpaRepository extends JpaRepository<Notificacao, String> {
}
