package com.ads.adshelpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.domain.dtos.TecnicoDTO;
import com.ads.adshelpdesk.repositories.TecnicoRepository;
import com.ads.adshelpdesk.services.exceptions.ObjectnotFoundException;

@Service 
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); // retorna um Option
		
		return obj.orElseThrow(() -> new ObjectnotFoundException("Obejto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		
		objDTO.setId(null);
		
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
		
	}
	
}
