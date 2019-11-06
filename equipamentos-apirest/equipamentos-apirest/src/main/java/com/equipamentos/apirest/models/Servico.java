package com.equipamentos.apirest.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
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
@Table(name = "SERVICO")
public class Servico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_servico;

	@NotNull
	private String status;

	@Column
	private String data_cadastro;

	@Column
	private String data_termino;

	public String getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getData_termino() {
		return data_termino;
	}

	public void setData_termino(String data_termino) {
		this.data_termino = data_termino;
	}

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;

	public long getId_servico() {
		return id_servico;
	}

	public void setId_servico(long id_servico) {
		this.id_servico = id_servico;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Servico(@NotNull String status, String data_cadastro, Cliente cliente, Equipamento equipamento) {
		super();
		this.status = status;
		this.data_cadastro = data_cadastro;
		this.cliente = cliente;
		this.equipamento = equipamento;
	}

	public Servico() {

	}

}