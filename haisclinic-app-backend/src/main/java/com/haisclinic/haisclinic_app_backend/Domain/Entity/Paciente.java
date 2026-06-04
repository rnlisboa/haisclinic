package com.haisclinic.haisclinic_app_backend.Domain.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name = "pacientes")
public class Paciente {

	@Id
	private String id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@Column(nullable = false)
	private boolean ativo;

	@Column(unique = true)
	private String email;

	@Column(columnDefinition = "text")
	private String observacoes;

	@Column(name = "criado_em", nullable = false)
	private LocalDateTime criadoEm;

	@Column(name = "atualizado_em", nullable = false)
	private LocalDateTime atualizadoEm;

	private String foto;

	public static Paciente criar(String nome, String sobrenome, LocalDate dataNascimento, String email,
			String observacoes, String foto) {
		LocalDateTime agora = LocalDateTime.now();
		return Paciente.builder()
			.id(UUID.randomUUID().toString())
			.nome(nome)
			.sobrenome(sobrenome)
			.dataNascimento(dataNascimento)
			.ativo(true)
			.email(email)
			.observacoes(observacoes)
			.criadoEm(agora)
			.atualizadoEm(agora)
			.foto(foto)
			.build();
	}

	public void atualizar(String nome, String sobrenome, LocalDate dataNascimento, Boolean ativo, String email,
			String observacoes, String foto) {
		if (nome != null) {
			this.nome = nome;
		}
		if (sobrenome != null) {
			this.sobrenome = sobrenome;
		}
		if (dataNascimento != null) {
			this.dataNascimento = dataNascimento;
		}
		if (ativo != null) {
			this.ativo = ativo;
		}
		if (email != null) {
			this.email = email;
		}
		if (observacoes != null) {
			this.observacoes = observacoes;
		}
		if (foto != null) {
			this.foto = foto;
		}
		this.atualizadoEm = LocalDateTime.now();
	}
}
