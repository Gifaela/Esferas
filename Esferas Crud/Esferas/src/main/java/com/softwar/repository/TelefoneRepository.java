package com.softwar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softwar.esferas.modal.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	
}
