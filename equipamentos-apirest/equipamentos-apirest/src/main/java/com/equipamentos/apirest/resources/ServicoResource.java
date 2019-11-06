package com.equipamentos.apirest.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
	
	@ApiOperation(value="Retorna um servico unico")
	@GetMapping("/servico/{id}")
	public Servico GetServicoById(@PathVariable(value="id") long id){
		return servicoRepository.findById(id);
	}
	
	@ApiOperation(value="Salva um serviço")
	@PostMapping("/servico")
	public Servico salvaServico(@RequestBody @Valid ServicoRequest servicoRequest) {
		
		Equipamento equipamentoSave = new Equipamento(servicoRequest.getTipo(),
				servicoRequest.getMarca(),
				servicoRequest.getProblema()
	    );
		
		Equipamento equipamento = equipamentoRepository.saveAndFlush(equipamentoSave);
		
		Cliente cliente = clienteResource.GetClienteById(servicoRequest.getId_cliente());
		
		String data_cadastro = servicoRequest.getData_cadastro();
		
		Servico servico = new Servico("A",data_cadastro, cliente, equipamento);
		
		return servicoRepository.save(servico);
	}
	
	
	@ApiOperation(value="Finaliza um servico")
	@PutMapping("/finalizaServico")
	public Servico finalizaServico(@RequestBody @Valid long id_servico) {
		
		Servico servicoUpdate = servicoRepository.findById(id_servico);
		String status = servicoUpdate.getStatus().equals("A")?"F":"A";
		servicoUpdate.setStatus(status);	
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String data_termino = sourceFormat.format(new Date());
		servicoUpdate.setData_termino(data_termino);
		return servicoRepository.save(servicoUpdate);
	}
	
	

}