package com.haisclinic.haisclinic_app_backend.Controllers;

import com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente.AtualizarPacienteUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente.BuscarPacienteUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente.CriarPacienteUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente.DeletarPacienteUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Paciente.SalvarFotoPacienteUseCase;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.AtualizarPacienteRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.CriarPacienteRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Paciente.PacienteResponse;
import com.haisclinic.haisclinic_app_backend.Exceptions.AlreadyExistsException;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import jakarta.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

	private final CriarPacienteUseCase criarPacienteUseCase;
	private final BuscarPacienteUseCase buscarPacienteUseCase;
	private final AtualizarPacienteUseCase atualizarPacienteUseCase;
	private final DeletarPacienteUseCase deletarPacienteUseCase;
	private final SalvarFotoPacienteUseCase salvarFotoPacienteUseCase;

	@PostMapping
	public ResponseEntity<PacienteResponse> criar(@Valid @RequestBody CriarPacienteRequest request) {
		PacienteResponse response = criarPacienteUseCase.execute(request);
		return ResponseEntity.created(URI.create("/pacientes/" + response.id())).body(response);
	}

	@GetMapping
	public ResponseEntity<List<PacienteResponse>> buscarTodos() {
		return ResponseEntity.ok(buscarPacienteUseCase.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(buscarPacienteUseCase.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PacienteResponse> atualizar(@PathVariable String id,
			@Valid @RequestBody AtualizarPacienteRequest request) {
		return ResponseEntity.ok(atualizarPacienteUseCase.execute(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		deletarPacienteUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/{id}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PacienteResponse> salvarFoto(@PathVariable String id, @RequestPart("file") MultipartFile file)
			throws IOException {
		PacienteResponse response = salvarFotoPacienteUseCase.execute(id, file.getOriginalFilename(),
				file.getContentType(), file.getBytes());

		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ProblemDetail> notFound(NotFoundException exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
	}

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ProblemDetail> alreadyExists(AlreadyExistsException exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
	}

	@ExceptionHandler({ IllegalArgumentException.class, IOException.class })
	public ResponseEntity<ProblemDetail> badRequest(Exception exception) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
		return ResponseEntity.badRequest().body(problemDetail);
	}
}
