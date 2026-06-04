package com.haisclinic.haisclinic_app_backend.Controllers;

import com.haisclinic.haisclinic_app_backend.Application.UseCases.Notificacao.BuscarNotificacaoUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Notificacao.MarcarNotificacaoLidaUseCase;
import com.haisclinic.haisclinic_app_backend.DTO.Notificacao.NotificacaoResponse;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

	private final BuscarNotificacaoUseCase buscarNotificacaoUseCase;
	private final MarcarNotificacaoLidaUseCase marcarNotificacaoLidaUseCase;

	@GetMapping
	public ResponseEntity<List<NotificacaoResponse>> buscarTodos() {
		return ResponseEntity.ok(buscarNotificacaoUseCase.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<NotificacaoResponse> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(buscarNotificacaoUseCase.buscarPorId(id));
	}

	@PatchMapping("/{id}/lida")
	public ResponseEntity<NotificacaoResponse> marcarComoLida(@PathVariable String id) {
		return ResponseEntity.ok(marcarNotificacaoLidaUseCase.execute(id));
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ProblemDetail> notFound(NotFoundException exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
	}
}
