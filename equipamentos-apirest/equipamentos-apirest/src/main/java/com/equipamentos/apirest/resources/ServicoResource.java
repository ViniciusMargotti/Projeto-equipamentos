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
import org.springframework.web.bind.annotation.RestController;

import com.equipamentos.apirest.DTO.ServicoRequest;
import com.equipamentos.apirest.models.Cliente;
import com.equipamentos.apirest.models.Equipamento;
import com.equipamentos.apirest.models.Servico;
import com.equipamentos.apirest.repository.EquipamentoRepository;
import com.equipamentos.apirest.repository.ServicoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Servicos")
public class ServicoResource {
	
	@Autowired
	ServicoRepository servicoRepository;
	
	@Autowired
	ClienteResource clienteResource;
	
	@Autowired
	EquipamentoRepository equipamentoRepository;
	
	@ApiOperation(value="Retorna uma lista de Servicos")
	@GetMapping("/servicos")
	public List<Servico> listaServicos(){
		return servicoRepository.findAll();
	}
	
	@ApiOperation(value="Salva um serviço")
	@PostMapping("/servico")
	public Servico salvaServico(@RequestBody @Valid ServicoRequest servicoRequest) {
		
		Equipamento equipamentoSave = new Equipamento(servicoRequest.getTipo(),
				servicoRequest.getMarca(),
				servicoRequest.getProblema()
	    );
		
		Equipamento Equipamento = equipamentoRepository.saveAndFlush(equipamentoSave);
		
		Cliente cliente = clienteResource.GetClienteById(servicoRequest.getId_cliente());
		
		Servico servico = new Servico("A", cliente, Equipamento);
		
		
		return servicoRepository.save(servico);
	}
	
	@ApiOperation(value="Atualiza um servico")
	@PutMapping("/servico")
	public Servico atualizaServico(@RequestBody @Valid Servico servico) {
		return servicoRepository.save(servico);
	}
	
	@ApiOperation(value="Atualiza um servico")
	@PutMapping("/finalizaServico")
	public Servico finalizaServico(@RequestBody @Valid long id_servico) {
		
		Servico servicoUpdate = servicoRepository.findById(id_servico);
		String status = servicoUpdate.getStatus().equals("A")?"F":"A";
		servicoUpdate.setStatus(status);	
		return servicoRepository.save(servicoUpdate);
	}
	
	

}