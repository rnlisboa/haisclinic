package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Notificacao;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.NotificacaoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificacaoRepositoryImpl implements NotificacaoRepository {

	private final NotificacaoJpaRepository notificacaoJpaRepository;

	@Override
	public Notificacao salvar(Notificacao notificacao) {
		return notificacaoJpaRepository.save(notificacao);
	}

	@Override
	public Optional<Notificacao> buscarPorId(String id) {
		return notificacaoJpaRepository.findById(id);
	}

	@Override
	public List<Notificacao> buscarTodos() {
		return notificacaoJpaRepository.findAll();
	}
}
