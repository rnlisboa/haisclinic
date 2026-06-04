package com.haisclinic.haisclinic_app_backend.Domain.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificacoes")
public class Notificacao {

	@Id
	private String id;

	@Column(name = "paciente_id", nullable = false)
	private String pacienteId;

	@Column(name = "prontuario_id", nullable = false)
	private String prontuarioId;

	@Column(name = "evolucao_id", nullable = false)
	private String evolucaoId;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false, columnDefinition = "text")
	private String mensagem;

	@Column(nullable = false)
	private boolean lida;

	@Column(name = "criado_em", nullable = false)
	private LocalDateTime criadoEm;

	public static Notificacao criar(String pacienteId, String prontuarioId, String evolucaoId, String titulo,
			String mensagem) {
		return Notificacao.builder()
			.id(UUID.randomUUID().toString())
			.pacienteId(pacienteId)
			.prontuarioId(prontuarioId)
			.evolucaoId(evolucaoId)
			.titulo(titulo)
			.mensagem(mensagem)
			.lida(false)
			.criadoEm(LocalDateTime.now())
			.build();
	}

	public void marcarComoLida() {
		this.lida = true;
	}
}
