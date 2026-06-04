package com.haisclinic.haisclinic_app_backend.Domain.Repository;

public interface ProfissionalImagemRepository {

	String salvar(String profissionalId, String nomeOriginal, String contentType, byte[] conteudo);
}
