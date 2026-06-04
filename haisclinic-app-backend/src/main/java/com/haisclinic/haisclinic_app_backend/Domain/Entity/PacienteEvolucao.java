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
@Table(name = "paciente_evolucoes")
public class PacienteEvolucao {

	@Id
	private String id;

	@Column(name = "prontuario_id", nullable = false)
	private String prontuarioId;

	@Column(nullable = false, columnDefinition = "text")
	private String evolucao;

	@Column(name = "criado_em", nullable = false)
	private LocalDateTime criadoEm;

	public static PacienteEvolucao criar(String prontuarioId, String evolucao) {
		return PacienteEvolucao.builder()
			.id(UUID.randomUUID().toString())
			.prontuarioId(prontuarioId)
			.evolucao(evolucao)
			.criadoEm(LocalDateTime.now())
			.build();
	}
}
