package com.haisclinic.haisclinic_app_backend.Mapper;

import com.haisclinic.haisclinic_app_backend.DTO.Notificacao.NotificacaoResponse;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Notificacao;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoMapper {

	public NotificacaoResponse toResponse(Notificacao notificacao) {
		return new NotificacaoResponse(notificacao.getId(), notificacao.getTitulo(), notificacao.getMensagem(),
				notificacao.isLida(), notificacao.getCriadoEm());
	}
}
