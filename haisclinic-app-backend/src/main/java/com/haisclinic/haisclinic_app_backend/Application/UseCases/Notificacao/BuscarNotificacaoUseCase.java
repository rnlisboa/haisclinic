package com.haisclinic.haisclinic_app_backend.Application.UseCases.Notificacao;

import com.haisclinic.haisclinic_app_backend.DTO.Notificacao.NotificacaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.NotificacaoRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.NotificacaoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarNotificacaoUseCase {

	private final NotificacaoRepository notificacaoRepository;
	private final NotificacaoMapper notificacaoMapper;

	public NotificacaoResponse buscarPorId(String id) {
		return notificacaoRepository.buscarPorId(id)
			.map(notificacaoMapper::toResponse)
			.orElseThrow(() -> new NotFoundException("Notificação não encontrada."));
	}

	public List<NotificacaoResponse> buscarTodos() {
		return notificacaoRepository.buscarTodos().stream().map(notificacaoMapper::toResponse).toList();
	}
}
