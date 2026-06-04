package com.haisclinic.haisclinic_app_backend.Application.UseCases.Notificacao;

import com.haisclinic.haisclinic_app_backend.DTO.Notificacao.NotificacaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Notificacao;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.NotificacaoRepository;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Mapper.NotificacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarcarNotificacaoLidaUseCase {

	private final NotificacaoRepository notificacaoRepository;
	private final NotificacaoMapper notificacaoMapper;

	public NotificacaoResponse execute(String id) {
		Notificacao notificacao = notificacaoRepository.buscarPorId(id)
			.orElseThrow(() -> new NotFoundException("Notificação não encontrada."));

		notificacao.marcarComoLida();
		return notificacaoMapper.toResponse(notificacaoRepository.salvar(notificacao));
	}
}
