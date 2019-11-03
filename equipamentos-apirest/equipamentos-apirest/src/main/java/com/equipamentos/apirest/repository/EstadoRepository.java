package com.equipamentos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.equipamentos.apirest.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	Estado findById(long id);
	
	
}