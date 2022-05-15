package com.softwar.esferas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwar.esferas.repository.PessoaRepository;
import com.softwar.esferas.repository.TelefoneRepository;
import com.softwar.esferas.modal.Telefone;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Telefone>> getAll() {
		return ResponseEntity.ok(telefoneRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Telefone> getById(@PathVariable Long id) {
		return telefoneRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/tel/{telefone}")
	public ResponseEntity<List<Telefone>> getByTelefone(@PathVariable String telefone){
		return ResponseEntity.ok(telefoneRepository.findAllByTelefoneContainingIgnoreCase(telefone));
	}

	@PostMapping
	public ResponseEntity<Telefone> postTelefone(@Valid @RequestBody Telefone telefone) {
		if (pessoaRepository.existsById(telefone.getPessoa().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(telefoneRepository.save(telefone));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<Telefone> putTelefone(@Valid @RequestBody Telefone telefone) {
		if (pessoaRepository.existsById(telefone.getPessoa().getId())) {
			return telefoneRepository.findById(telefone.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(telefoneRepository.save(telefone)))
					.orElse(ResponseEntity.notFound().build());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTelefone(@PathVariable Long id) {
		return telefoneRepository.findById(id).map(resposta -> {
			telefoneRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
