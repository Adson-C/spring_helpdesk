package com.ads.adshelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.adshelpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
}
