package com.softwar.esferas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softwar.esferas.modal.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	public List<Pessoa> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
