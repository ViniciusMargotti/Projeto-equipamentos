package com.equipamentos.apirest.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.equipamentos.apirest.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	Cidade findById(long id);
	
	  @Query(value = "SELECT * FROM CIDADE WHERE ID_ESTADO = ?1 ", nativeQuery = true)
	Collection<Cidade> getByEstado(long id);
    
}