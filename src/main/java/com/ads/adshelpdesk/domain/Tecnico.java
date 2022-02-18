package com.ads.adshelpdesk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
		addPerfis(Perfil.TECNICO);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.TECNICO);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
