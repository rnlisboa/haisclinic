package com.haisclinic.haisclinic_app_backend.Domain.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "prontuarios")
public class Prontuario {

	@Id
	private String id;

	@Column(name = "paciente_id", nullable = false)
	private String pacienteId;

	@Column(name = "profissional_id", nullable = false)
	private String profissionalId;

	@Column(columnDefinition = "text")
	private String queixa;

	@Column(columnDefinition = "text")
	private String historia;

	@Column(name = "criado_em", nullable = false)
	private LocalDateTime criadoEm;

	@Column(name = "atualizado_em", nullable = false)
	private LocalDateTime atualizadoEm;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusEnum status;

	public static Prontuario criar(String pacienteId, String profissionalId, String queixa, String historia,
			StatusEnum status) {
		LocalDateTime agora = LocalDateTime.now();
		return Prontuario.builder()
			.id(UUID.randomUUID().toString())
			.pacienteId(pacienteId)
			.profissionalId(profissionalId)
			.queixa(queixa)
			.historia(historia)
			.criadoEm(agora)
			.atualizadoEm(agora)
			.status(status == null ? StatusEnum.EM_ATENDIMENTO : status)
			.build();
	}
}
