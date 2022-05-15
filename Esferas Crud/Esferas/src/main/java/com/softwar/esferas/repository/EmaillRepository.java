package com.softwar.esferas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softwar.esferas.modal.Emaill;

@Repository
public interface EmaillRepository extends JpaRepository<Emaill, Long> {
	public List<Emaill> findAllByEmailContainingIgnoreCase(@Param("email") String email);
}
