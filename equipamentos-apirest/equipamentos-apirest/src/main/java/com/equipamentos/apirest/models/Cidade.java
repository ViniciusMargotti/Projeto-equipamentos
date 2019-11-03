package com.equipamentos.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="CIDADE")
public class Cidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_cidade;
	
	@NotNull
	private String nome;
	
	public long getId() {
		return id_cidade;
	}

	public void setId(long id) {
		this.id_cidade = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
     public long getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(long id_cidade) {
		this.id_cidade = id_cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	@ManyToOne
	 @JoinColumn(name = "id_estado")
	 private Estado estado;
     
     @OneToMany(fetch = FetchType.LAZY)
	 private List<Cliente> clientes;
	
	

	
	
	
	
	
	
	
}