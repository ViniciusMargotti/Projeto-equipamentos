package com.equipamentos.apirest.resources;

import java.util.Collection;
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

import com.equipamentos.apirest.models.Cidade;
import com.equipamentos.apirest.models.Cliente;
import com.equipamentos.apirest.models.Estado;
import com.equipamentos.apirest.repository.CidadeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Cidades")
public class CidadeResource {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@ApiOperation(value="Retorna uma lista de Cidades")
	@GetMapping("/cidades")
	public List<Cidade> listaCidades(){
		return cidadeRepository.findAll();
	}
	
	@ApiOperation(value="Retorna uma cidade pelo estado")
	@GetMapping("/cidadeByEstado/{id}")
	public Collection<Cidade> GetCidadeByEstado(@PathVariable(value="id") long id){
		return cidadeRepository.getByEstado(id);
	}
	
	
	@ApiOperation(value="Salva uma cidade")
	@PostMapping("/cidade")
	public Cidade salvaCidade(@RequestBody @Valid Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	

}