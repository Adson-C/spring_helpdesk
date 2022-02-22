package com.ads.adshelpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Pessoa;
import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.domain.dtos.TecnicoDTO;
import com.ads.adshelpdesk.repositories.PessoaRepository;
import com.ads.adshelpdesk.repositories.TecnicoRepository;
import com.ads.adshelpdesk.services.exceptions.DataIntegrityViolationException;
import com.ads.adshelpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); // retorna um Option

		return obj.orElseThrow(() -> new ObjectnotFoundException("Obejto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {

		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {

		objDTO.setId(null);
		validaPorCpfEmail(objDTO);

		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);

	}

	private void validaPorCpfEmail(TecnicoDTO objDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			
			throw new DataIntegrityViolationException("CPF ja Cadastrado no Sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
			if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			
			throw new DataIntegrityViolationException("E-mail ja Cadastrado no Sistema!");
	
			}
	}
	
}
