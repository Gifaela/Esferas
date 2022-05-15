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

import com.softwar.esferas.repository.EmaillRepository;
import com.softwar.esferas.repository.PessoaRepository;
import com.softwar.esferas.modal.Emaill;

@RestController
@RequestMapping("/email")
public class EmaillController {

	@Autowired
	private EmaillRepository emaillRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Emaill>> getAll() {
		return ResponseEntity.ok(emaillRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Emaill> getById(@PathVariable Long id) {
		return emaillRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/end/{email}")
	public ResponseEntity<List<Emaill>> getByEmail(@PathVariable String email) {
		return ResponseEntity.ok(emaillRepository.findAllByEmailContainingIgnoreCase(email));
	}

	@PostMapping
	public ResponseEntity<Emaill> postEmaill(@Valid @RequestBody Emaill emaill) {
		if (pessoaRepository.existsById(emaill.getPessoa().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(emaillRepository.save(emaill));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Emaill> putEmaill(@Valid @RequestBody Emaill emaill) {
		if (pessoaRepository.existsById(emaill.getPessoa().getId())) {
			return emaillRepository.findById(emaill.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(emaillRepository.save(emaill)))
					.orElse(ResponseEntity.notFound().build());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmaill(@PathVariable Long id) {

		return emaillRepository.findById(id).map(resposta -> {
			emaillRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		}).orElse(ResponseEntity.notFound().build());
	}

}
