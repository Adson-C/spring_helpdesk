package com.ads.adshelpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ads.adshelpdesk.domain.Cliente;
import com.ads.adshelpdesk.domain.dtos.ClienteDTO;
import com.ads.adshelpdesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes") // meu End-Point para tecnicos
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	// buscar por id
	@GetMapping(value = "/{id}") // variavel de path
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		
		Cliente obj = service.findById(id);
		
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	// buscar uma lista
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> list = service.findAll();
		
		List<ClienteDTO> listDTO = list.stream().map(Obj -> new ClienteDTO(Obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	// salvar 
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {
		Cliente newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	// Atualizar
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid  @RequestBody ClienteDTO objDTO) {
		
		Cliente obj = service.update(id, objDTO);
		
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	// deletar
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
