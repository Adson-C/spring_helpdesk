package com.ads.adshelpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	protected Integer id;
	protected String nome;
	
	protected String cpf;
	
	protected String email;
	
	protected String senha;

	protected Set<Integer> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy") // formado (dia mes ano)
	protected LocalDate dataCraicao = LocalDate.now(); // pega o momento atual

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico obj) { // pega A Class Tecnico
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCraicao = obj.getDataCraicao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCraicao() {
		return dataCraicao;
	}

	public void setDataCraicao(LocalDate dataCraicao) {
		this.dataCraicao = dataCraicao;
	}

	
}
