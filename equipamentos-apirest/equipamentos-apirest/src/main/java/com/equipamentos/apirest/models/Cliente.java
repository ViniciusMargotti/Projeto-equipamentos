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
@Table(name="CLIENTE")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_cliente;
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@NotNull
	private String nome;
	
	@NotNull
	private String telefone;
	
	@NotNull
	private String endereco;
	
	@NotNull
	private String email;

	public long getId() {
		return id_cliente;
	}

	public void setId(long id) {
		this.id_cliente = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 @ManyToOne
	 @JoinColumn(name = "id_cidade")
	 private Cidade cidade;
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private List<Servico> servicos;

	public Cliente(@NotNull String nome, @NotNull String telefone, @NotNull String endereco, @NotNull String email,
			Cidade cidade, List<Servico> servicos) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.cidade = cidade;
		this.servicos = servicos;
	}
	
    public Cliente() {
		
	}

	
	
	
	
}
