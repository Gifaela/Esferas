package com.softwar.esferas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwar.esferas.modal.Pessoa;
import com.softwar.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Não lembro porque eu usava
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll(){
		return ResponseEntity.ok(pessoaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getById(@PathVariable Long id){
		return pessoaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Pessoa> postPessoa(@Valid @RequestBody Pessoa pessoa){
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> putPessoa(@Valid @RequestBody Pessoa pessoa){
		return pessoaRepository.findById(pessoa.getId())
				.map(resposta -> ResponseEntity.ok().body(pessoaRepository.save(pessoa)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePessoa (@PathVariable Long id){
		return pessoaRepository.findById(id)
				.map(resposta ->{
					pessoaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
