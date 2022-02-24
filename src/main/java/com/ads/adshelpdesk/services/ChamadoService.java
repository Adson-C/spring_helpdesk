package com.ads.adshelpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Chamado;
import com.ads.adshelpdesk.repositories.ChamadoRepository;
import com.ads.adshelpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectnotFoundException("Obejto não encontrado! ID: " + id));
	}
	
}
