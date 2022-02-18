package com.ads.adshelpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnico") // meu End-Point para tecnicos
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	// buscar por id
	@GetMapping(value = "/{id}") // variavel de path
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
		
		Tecnico obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
