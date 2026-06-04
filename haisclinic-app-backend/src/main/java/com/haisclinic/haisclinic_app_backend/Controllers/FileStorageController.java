package com.haisclinic.haisclinic_app_backend.Controllers;

import com.haisclinic.haisclinic_app_backend.Domain.Entity.ArquivoImagem;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import com.haisclinic.haisclinic_app_backend.Infrastructure.Persistence.ImagemStorageService;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileStorageController {

	private final ImagemStorageService imagemStorageService;

	@GetMapping("/**")
	public ResponseEntity<Resource> visualizarImagem(HttpServletRequest request) {
		ArquivoImagem imagem = imagemStorageService.carregar(extrairCaminho(request));
		return responderImagem(imagem);
	}

	private ResponseEntity<Resource> responderImagem(ArquivoImagem imagem) {
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(imagem.contentType()))
			.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imagem.nomeArquivo() + "\"")
			.body(imagem.resource());
	}

	private String extrairCaminho(HttpServletRequest request) {
		String prefixo = request.getContextPath() + "/files/";
		String caminho = request.getRequestURI().substring(prefixo.length());
		return UriUtils.decode(caminho, StandardCharsets.UTF_8);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ProblemDetail> notFound(NotFoundException exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ProblemDetail> badRequest(IllegalArgumentException exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
		return ResponseEntity.badRequest().body(problemDetail);
	}
}
