package com.ads.adshelpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Cliente;
import com.ads.adshelpdesk.domain.Pessoa;
import com.ads.adshelpdesk.domain.dtos.ClienteDTO;
import com.ads.adshelpdesk.repositories.ClienteRepository;
import com.ads.adshelpdesk.repositories.PessoaRepository;
import com.ads.adshelpdesk.services.exceptions.DataIntegrityViolationException;
import com.ads.adshelpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id); // retorna um Option

		return obj.orElseThrow(() -> new ObjectnotFoundException("Obejto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {

		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {

		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha())); // encriptação da senha
		validaPorCpfEmail(objDTO);

		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);

	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);

		return repository.save(oldObj);

	}

	public void delete(Integer id) {

		Cliente obj = findById(id);
		if (obj.getChamados().size() > 0) {

			throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado!");
		}
		repository.deleteById(id);

	}

	private void validaPorCpfEmail(ClienteDTO objDTO) {

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
