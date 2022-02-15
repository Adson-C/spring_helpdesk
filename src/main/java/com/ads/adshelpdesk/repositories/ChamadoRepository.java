package com.ads.adshelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.adshelpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

	
}
