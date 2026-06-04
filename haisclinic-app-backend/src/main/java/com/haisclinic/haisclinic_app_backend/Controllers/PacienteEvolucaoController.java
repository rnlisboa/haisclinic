package com.haisclinic.haisclinic_app_backend.Controllers;

import com.haisclinic.haisclinic_app_backend.Application.UseCases.PacienteEvolucao.BuscarPacienteEvolucaoUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.PacienteEvolucao.CriarPacienteEvolucaoUseCase;
import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.CriarPacienteEvolucaoRequest;
import com.haisclinic.haisclinic_app_backend.DTO.PacienteEvolucao.PacienteEvolucaoResponse;
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
@RequestMapping("/paciente-evolucoes")
@RequiredArgsConstructor
public class PacienteEvolucaoController {

	private final CriarPacienteEvolucaoUseCase criarPacienteEvolucaoUseCase;
	private final BuscarPacienteEvolucaoUseCase buscarPacienteEvolucaoUseCase;

	@PostMapping
	public ResponseEntity<PacienteEvolucaoResponse> criar(@Valid @RequestBody CriarPacienteEvolucaoRequest request) {
		PacienteEvolucaoResponse response = criarPacienteEvolucaoUseCase.execute(request);
		return ResponseEntity.created(URI.create("/paciente-evolucoes/" + response.id())).body(response);
	}

	@GetMapping
	public ResponseEntity<List<PacienteEvolucaoResponse>> buscarTodos() {
		return ResponseEntity.ok(buscarPacienteEvolucaoUseCase.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacienteEvolucaoResponse> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(buscarPacienteEvolucaoUseCase.buscarPorId(id));
	}

	@GetMapping("/prontuario/{prontuarioId}")
	public ResponseEntity<List<PacienteEvolucaoResponse>> buscarPorProntuarioId(@PathVariable String prontuarioId) {
		return ResponseEntity.ok(buscarPacienteEvolucaoUseCase.buscarPorProntuarioId(prontuarioId));
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
