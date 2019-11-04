package com.equipamentos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.equipamentos.apirest.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
	Servico findById(long id);
	
}