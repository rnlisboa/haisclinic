package com.haisclinic.haisclinic_app_backend.Domain.Repository;

public interface PacienteImagemRepository {

	String salvar(String pacienteId, String nomeOriginal, String contentType, byte[] conteudo);
}
