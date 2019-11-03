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

import com.equipamentos.apirest.models.Cliente;
import com.equipamentos.apirest.repository.ClienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Clientes")
public class ClienteResource {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@ApiOperation(value="Retorna uma lista de Clientes")
	@GetMapping("/Clientes")
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	@ApiOperation(value="Retorna um cliente unico")
	@GetMapping("/cliente/{id}")
	public Cliente GetClienteById(@PathVariable(value="id") long id){
		return clienteRepository.findById(id);
	}
	
	@ApiOperation(value="Salva um cliente")
	@PostMapping("/cliente")
	public Cliente salvaCliente(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@ApiOperation(value="Deleta um cliente")
	@DeleteMapping("/cliente")
	public void deletaCliente(@RequestBody @Valid Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	@ApiOperation(value="Atualiza um cliente")
	@PutMapping("/cliente")
	public Cliente atualizaCliente(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	 

}
