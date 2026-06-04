package com.haisclinic.haisclinic_app_backend.Controllers;

import com.haisclinic.haisclinic_app_backend.Application.UseCases.Prontuario.BuscarProntuarioUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Prontuario.CriarProntuarioUseCase;
import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.CriarProntuarioRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Prontuario.ProntuarioResponse;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prontuarios")
@RequiredArgsConstructor
public class ProntuarioController {

	private final CriarProntuarioUseCase criarProntuarioUseCase;
	private final BuscarProntuarioUseCase buscarProntuarioUseCase;

	@PostMapping
	public ResponseEntity<ProntuarioResponse> criar(@Valid @RequestBody CriarProntuarioRequest request) {
		ProntuarioResponse response = criarProntuarioUseCase.execute(request);
		return ResponseEntity.created(URI.create("/prontuarios/" + response.id())).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ProntuarioResponse>> buscarTodos() {
		return ResponseEntity.ok(buscarProntuarioUseCase.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProntuarioResponse> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(buscarProntuarioUseCase.buscarPorId(id));
	}

	@GetMapping("/paciente/{pacienteId}")
	public ResponseEntity<List<ProntuarioResponse>> buscarPorPacienteId(@PathVariable String pacienteId) {
		return ResponseEntity.ok(buscarProntuarioUseCase.buscarPorPacienteId(pacienteId));
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
