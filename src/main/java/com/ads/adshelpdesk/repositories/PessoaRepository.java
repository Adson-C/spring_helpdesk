package com.ads.adshelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.adshelpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	
}
