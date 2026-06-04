package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.ArquivoImagem;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class ImagemStorageService {

	private final Path imagesDir;

	public ImagemStorageService(@Value("${app.storage.images-dir:uploads/images}") String imagesDir) {
		this.imagesDir = Path.of(imagesDir).toAbsolutePath().normalize();
	}

	public ArquivoImagem carregar(String caminho) {
		if (caminho == null || caminho.isBlank()) {
			throw new IllegalArgumentException("Caminho da imagem não informado.");
		}

		Path arquivo = imagesDir.resolve(caminho).normalize();

		if (!arquivo.startsWith(imagesDir)) {
			throw new IllegalArgumentException("Caminho da imagem inválido.");
		}
		if (!Files.isRegularFile(arquivo)) {
			throw new NotFoundException("Imagem não encontrada.");
		}

		try {
			String contentType = Files.probeContentType(arquivo);
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return new ArquivoImagem(arquivo.getFileName().toString(), contentType, new FileSystemResource(arquivo));
		} catch (IOException exception) {
			throw new IllegalStateException("Não foi possível ler a imagem.", exception);
		}
	}
}
