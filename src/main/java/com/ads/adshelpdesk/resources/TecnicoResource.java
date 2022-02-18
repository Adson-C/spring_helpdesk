package com.ads.adshelpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.domain.dtos.TecnicoDTO;
import com.ads.adshelpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos") // meu End-Point para tecnicos
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	// buscar por id
	@GetMapping(value = "/{id}") // variavel de path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		
		Tecnico obj = service.findById(id);
		
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	// buscar uma lista
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		
		List<Tecnico> list = service.findAll();
		
		List<TecnicoDTO> listDTO = list.stream().map(Obj -> new TecnicoDTO(Obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}

}
