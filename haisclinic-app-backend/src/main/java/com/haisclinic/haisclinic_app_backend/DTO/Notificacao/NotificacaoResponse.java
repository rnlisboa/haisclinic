package com.haisclinic.haisclinic_app_backend.DTO.Notificacao;

import java.time.LocalDateTime;

public record NotificacaoResponse(
		String id,
		String titulo,
		String mensagem,
		boolean lida,
		LocalDateTime criadoEm) {
}
