package com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence;

import com.haisclinic.haisclinic_app_backend.Domain.Repository.PacienteImagemRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PacienteImagemRepositoryImpl implements PacienteImagemRepository {

	private final Path imagesDir;

	public PacienteImagemRepositoryImpl(@Value("${app.storage.images-dir:uploads/images}") String imagesDir) {
		this.imagesDir = Path.of(imagesDir).toAbsolutePath().normalize();
	}

	@Override
	public String salvar(String pacienteId, String nomeOriginal, String contentType, byte[] conteudo) {
		validarImagem(contentType, conteudo);

		try {
			Files.createDirectories(imagesDir);
			String extensao = resolverExtensao(nomeOriginal, contentType);
			String nomeArquivo = pacienteId + extensao;
			Path destino = imagesDir.resolve(nomeArquivo).normalize();

			if (!destino.startsWith(imagesDir)) {
				throw new IllegalArgumentException("Nome de arquivo inválido.");
			}

			Files.write(destino, conteudo, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			return nomeArquivo;
		} catch (IOException exception) {
			throw new IllegalStateException("Não foi possível salvar a imagem do paciente.", exception);
		}
	}

	private void validarImagem(String contentType, byte[] conteudo) {
		if (conteudo == null || conteudo.length == 0) {
			throw new IllegalArgumentException("Imagem vazia.");
		}
		if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith("image/")) {
			throw new IllegalArgumentException("Arquivo enviado não é uma imagem.");
		}
	}

	private String resolverExtensao(String nomeOriginal, String contentType) {
		if (nomeOriginal != null) {
			int indice = nomeOriginal.lastIndexOf('.');
			if (indice >= 0 && indice < nomeOriginal.length() - 1) {
				return nomeOriginal.substring(indice).toLowerCase(Locale.ROOT);
			}
		}

		return switch (contentType.toLowerCase(Locale.ROOT)) {
			case "image/png" -> ".png";
			case "image/webp" -> ".webp";
			case "image/gif" -> ".gif";
			default -> ".jpg";
		};
	}
}
