package com.equipamentos.apirest.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equipamentos.apirest.models.Equipamento;
import com.equipamentos.apirest.repository.EquipamentoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Equipamentos")
public class EquipamentoResource {
	
	@Autowired
	EquipamentoRepository equipamentoRepository;
	
	@ApiOperation(value="Retorna uma lista de equipamentos")
	@GetMapping("/equipamentos")
	public List<Equipamento> listaEquipamentos(){
		return equipamentoRepository.findAll();
	}
	
	@ApiOperation(value="Retorna um equipamento unico")
	@GetMapping("/equipamento/{id}")
	public Equipamento listaEquipamentoUnico(@PathVariable(value="id") long id){
		return equipamentoRepository.findById(id);
	}
	
	@ApiOperation(value="Salva um equipamento")
	@PostMapping("/equipamento")
	public Equipamento salvaEquipamento(@RequestBody @Valid Equipamento equipamento) {
		return equipamentoRepository.save(equipamento);
	}
	
	@ApiOperation(value="Deleta um equipamento")
	@DeleteMapping("/equipamento")
	public void deletaEquipamento(@RequestBody @Valid Equipamento equipamento) {
		equipamentoRepository.delete(equipamento);
	}
	
	@ApiOperation(value="Atualiza um equipamento")
	@PutMapping("/equipamento")
	public Equipamento atualizaEquipamento(@RequestBody @Valid Equipamento equipamento) {
		return equipamentoRepository.save(equipamento);
	}
	 

}
