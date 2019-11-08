package com.equipamentos.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.equipamentos.apirest.models.Cidade;
import com.equipamentos.apirest.models.Estado;
import com.equipamentos.apirest.resources.CidadeResource;
import com.equipamentos.apirest.resources.EstadoResource;
import com.equipamentos.apirest.resources.ServicoResource;

@SpringBootApplication
public class ApiRestApplication {
	
	 @Autowired
	 ServicoResource servicoResource;
	 @Autowired
	 EstadoResource estadoResource;
	 @Autowired
	 CidadeResource cidadeResource;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner seedServicosTable() {
		 return args -> { 
		List<Estado> estados = estadoResource.listaEstados();
		if(estados.size()==0) {
	
		  Estado santaCatarina = estadoResource.salvaEstado(new Estado("SC"));
		  Estado parana= estadoResource.salvaEstado( new Estado("PR"));
		  Estado rioGrandedoSul = estadoResource.salvaEstado(new Estado("RS"));

		  Cidade criciuma = new Cidade("Criciúma",santaCatarina);
		  cidadeResource.salvaCidade(criciuma);
		  Cidade curitiba = new Cidade("Curitiba",parana);
		  cidadeResource.salvaCidade(curitiba);
		  Cidade portoAlegre = new Cidade("Porto Alegre",rioGrandedoSul);
		  cidadeResource.salvaCidade(portoAlegre);	  
		 }
		
		 };
		 
		
		
	}
	
	
}
