package com.softwar.esferas.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;	

@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome não foi informado")
	@Size(min = 3, max = 15)
	private String nome;

	@NotBlank(message = "Sobrenome não foi informado")
	@Size(min = 3, max = 15)
	private String sobrenome;

	@NotBlank(message = "CPF não foi informado")
	@CPF(message = "Campo inválido")
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
	private String cpf;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("pessoa")
	private List<Emaill> emaill;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("pessoa")
	private List<Telefone> telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Emaill> getEmaill() {
		return emaill;
	}

	public void setEmaill(List<Emaill> emaill) {
		this.emaill = emaill;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

}
