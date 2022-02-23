package com.ads.adshelpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.ads.adshelpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity // cria tabela Pessoa
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id // jave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // gerar id diferentes
	protected Integer id;
	protected String nome;
	
	@Column(unique = true) // unico 
	
	@CPF
	protected String cpf;
	
	@Column(unique = true)
	protected String email;
	
	protected String senha;

	@ElementCollection(fetch = FetchType.EAGER) //
	@CollectionTable(name = "PERFIS") // cria tabelas de perfis
	protected Set<Integer> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy") // formado (dia mes ano)
	protected LocalDate dataCraicao = LocalDate.now(); // pega o momento atual

	public Pessoa() {
		super();
		addPerfis(Perfil.CLIENTE); //  cadastros ja viram como clientes 
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfis(Perfil.CLIENTE); //  cadastros ja viram como clientes
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
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); // converte Integer para Set
	}

	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo()); // addPerfis 
	}

	public LocalDate getDataCraicao() {
		return dataCraicao;
	}

	public void setDataCraicao(LocalDate dataCraicao) {
		this.dataCraicao = dataCraicao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

}
