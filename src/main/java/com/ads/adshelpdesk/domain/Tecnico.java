package com.ads.adshelpdesk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ads.adshelpdesk.domain.dtos.TecnicoDTO;
import com.ads.adshelpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore  // ignora a lista 
	@OneToMany(mappedBy = "tecnico") //foi chamado por tecnico (um para muitos)
	private List<Chamado> chamados = new ArrayList<>(); // pega objtos da classe Chamados

	public Tecnico() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.CLIENTE);
	}
	
	public Tecnico(TecnicoDTO obj) { // pega A Class Tecnico
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCraicao = obj.getDataCraicao();
	}
	

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
