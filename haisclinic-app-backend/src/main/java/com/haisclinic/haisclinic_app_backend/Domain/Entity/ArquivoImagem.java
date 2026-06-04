package com.haisclinic.haisclinic_app_backend.Domain.Entity;

import org.springframework.core.io.Resource;

public record ArquivoImagem(String nomeArquivo, String contentType, Resource resource) {
}
