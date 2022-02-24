package com.ads.adshelpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Chamado;
import com.ads.adshelpdesk.domain.Cliente;
import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.domain.dtos.ChamadoDTO;
import com.ads.adshelpdesk.domain.enums.Prioridade;
import com.ads.adshelpdesk.domain.enums.Status;
import com.ads.adshelpdesk.repositories.ChamadoRepository;
import com.ads.adshelpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectnotFoundException("Obejto não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {

		return repository.findAll();
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id); // informações desatualizadas
		oldObj = newChamado(objDTO); // atualizadas
		
		return repository.save(oldObj);
	}

	// salvar
	public Chamado create(@Valid ChamadoDTO objDTO) {

		return repository.save(newChamado(objDTO)); // retorna newChamado

	}

	// newChamado Tanto para atualizar e para salvar
	private Chamado newChamado(ChamadoDTO obj) {

		// vereficar se ID do tecnico e cliente existe
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());

		// caso exista oID do tecnico e cliente
		Chamado chamado = new Chamado();
		if (obj.getId() != null) {

			chamado.setId(obj.getId());

		}
		
		if (obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}

}
