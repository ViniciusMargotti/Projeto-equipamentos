package com.equipamentos.apirest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="SERVICO")
public class Servico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_servico;
	
	@NotNull
	private String status;
	
	 @ManyToOne
	 @JoinColumn(name = "id_cliente")
	 private Cliente cliente;
	 
	 @OneToOne
	 @JoinColumn(name = "id_equipamento")
	 private Equipamento equipamento;
     
	 
     
	
	

	
	
	
	
	
	
	
}