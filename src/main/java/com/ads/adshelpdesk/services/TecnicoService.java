package com.ads.adshelpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.repositories.TecnicoRepository;

@Service 
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); // retorna um Option
		
		return obj.orElse(null);
	}
	
}
