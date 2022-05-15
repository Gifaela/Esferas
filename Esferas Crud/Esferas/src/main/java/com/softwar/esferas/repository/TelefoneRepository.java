package com.softwar.esferas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softwar.esferas.modal.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	public List<Telefone> findAllByTelefoneContainingIgnoreCase(@Param("telefone") String telefone);
	
}
