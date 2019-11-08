package com.equipamentos.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.equipamentos.apirest.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
	Servico findById(long id);
	
	@Query(value = "SELECT * FROM SERVICO s WHERE s.status = ?1", 
			  nativeQuery = true)
			List<Servico> getServicosByStatus(String status);
	
}