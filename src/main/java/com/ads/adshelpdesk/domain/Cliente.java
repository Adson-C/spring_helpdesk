package com.ads.adshelpdesk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ads.adshelpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore  // ignora a lista
	@OneToMany(mappedBy = "cliente") //foi chamado por cliente (um para muitos)
	private List<Chamado> chamados = new ArrayList<>(); // pega objtos da classe Chamados

	public Cliente() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
