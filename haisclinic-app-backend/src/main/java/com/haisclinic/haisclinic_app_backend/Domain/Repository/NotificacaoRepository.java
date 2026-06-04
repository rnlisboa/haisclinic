package com.haisclinic.haisclinic_app_backend.Domain.Repository;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.Notificacao;
import java.util.List;
import java.util.Optional;

public interface NotificacaoRepository {

	Notificacao salvar(Notificacao notificacao);

	Optional<Notificacao> buscarPorId(String id);

	List<Notificacao> buscarTodos();
}
