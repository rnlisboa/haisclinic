package com.haisclinic.haisclinic_app_backend.Controllers;

import com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional.AtualizarProfissionalUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional.BuscarProfissionalUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional.CriarProfissionalUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional.DeletarProfissionalUseCase;
import com.haisclinic.haisclinic_app_backend.Application.UseCases.Profissional.SalvarFotoProfissionalUseCase;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.AtualizarProfissionalRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.CriarProfissionalRequest;
import com.haisclinic.haisclinic_app_backend.DTO.Profissional.ProfissionalResponse;
import com.haisclinic.haisclinic_app_backend.Exceptions.AlreadyExistsException;
import com.haisclinic.haisclinic_app_backend.Exceptions.NotFoundException;
import jakarta.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

	private final CriarProfissionalUseCase criarProfissionalUseCase;
	private final BuscarProfissionalUseCase buscarProfissionalUseCase;
	private final AtualizarProfissionalUseCase atualizarProfissionalUseCase;
	private final DeletarProfissionalUseCase deletarProfissionalUseCase;
	private final SalvarFotoProfissionalUseCase salvarFotoProfissionalUseCase;

	@PostMapping
	public ResponseEntity<ProfissionalResponse> criar(@Valid @RequestBody CriarProfissionalRequest request) {
		ProfissionalResponse response = criarProfissionalUseCase.execute(request);
		return ResponseEntity.created(URI.create("/profissionais/" + response.id())).body(response);
	}

	@GetMapping
	public ResponseEntity<List<ProfissionalResponse>> buscarTodos() {
		return ResponseEntity.ok(buscarProfissionalUseCase.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfissionalResponse> buscarPorId(@PathVariable String id) {
		return ResponseEntity.ok(buscarProfissionalUseCase.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfissionalResponse> atualizar(@PathVariable String id,
			@Valid @RequestBody AtualizarProfissionalRequest request) {
		return ResponseEntity.ok(atualizarProfissionalUseCase.execute(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		deletarProfissionalUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/{id}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProfissionalResponse> salvarFoto(@PathVariable String id,
			@RequestPart("file") MultipartFile file) throws IOException {
		ProfissionalResponse response = salvarFotoProfissionalUseCase.execute(id, file.getOriginalFilename(),
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
