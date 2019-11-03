package com.equipamentos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipamentos.apirest.models.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
	Equipamento findById(long id);

}
