package com.haisclinic.haisclinic_app_backend.Application.Listeners;

import com.haisclinic.haisclinic_app_backend.Application.Events.EvolucaoCriadaEvent;
import com.haisclinic.haisclinic_app_backend.Domain.Entity.Notificacao;
import com.haisclinic.haisclinic_app_backend.Domain.Repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvolucaoCriadaListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(EvolucaoCriadaListener.class);

	private final NotificacaoRepository notificacaoRepository;

	@Async
	@EventListener
	public void handle(EvolucaoCriadaEvent event) {
		try {
			Notificacao notificacao = Notificacao.criar(
					event.pacienteId(),
					event.prontuarioId(),
					event.evolucaoId(),
					"Nova evolução clínica registrada",
					"Uma nova evolução clínica foi registrada para o paciente " + event.nomePaciente());

			notificacaoRepository.salvar(notificacao);
		}
		catch (Exception exception) {
			LOGGER.error("Não foi possível gerar a notificação da evolução clínica.", exception);
		}
	}
}
