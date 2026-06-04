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
@Table(name = "profissionais")
public class Profissional {

	@Id
	private String id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;

	@Column(nullable = false)
	private String especialidade;

	@Column(unique = true)
	private String email;

	@Column(name = "criado_em", nullable = false)
	private LocalDateTime criadoEm;

	@Column(name = "atualizado_em", nullable = false)
	private LocalDateTime atualizadoEm;

	private String foto;

	public static Profissional criar(String nome, String sobrenome, String especialidade, String email, String foto) {
		LocalDateTime agora = LocalDateTime.now();
		return Profissional.builder()
			.id(UUID.randomUUID().toString())
			.nome(nome)
			.sobrenome(sobrenome)
			.especialidade(especialidade)
			.email(email)
			.criadoEm(agora)
			.atualizadoEm(agora)
			.foto(foto)
			.build();
	}

	public void atualizar(String nome, String sobrenome, String especialidade, String email, String foto) {
		if (nome != null) {
			this.nome = nome;
		}
		if (sobrenome != null) {
			this.sobrenome = sobrenome;
		}
		if (especialidade != null) {
			this.especialidade = especialidade;
		}
		if (email != null) {
			this.email = email;
		}
		if (foto != null) {
			this.foto = foto;
		}
		this.atualizadoEm = LocalDateTime.now();
	}
}
