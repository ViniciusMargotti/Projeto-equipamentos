package com.equipamentos.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="ESTADO")
public class Estado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_estado;
	
	@NotNull
	private String nome;

	public long getId() {
		return id_estado;
	}

	public void setId(long id) {
		this.id_estado = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	 @OneToMany(fetch = FetchType.LAZY)
	 private List<Cidade> cidades;

	public Estado(@NotNull String nome) {
		super();
		this.nome = nome;
	}
	
	public Estado() {
		
	}
	
	 
	 
	
	
	
	
}