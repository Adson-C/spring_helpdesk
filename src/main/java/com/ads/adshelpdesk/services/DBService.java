package com.ads.adshelpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.adshelpdesk.domain.Chamado;
import com.ads.adshelpdesk.domain.Cliente;
import com.ads.adshelpdesk.domain.Tecnico;
import com.ads.adshelpdesk.domain.enums.Perfil;
import com.ads.adshelpdesk.domain.enums.Prioridade;
import com.ads.adshelpdesk.domain.enums.Status;
import com.ads.adshelpdesk.repositories.ChamadoRepository;
import com.ads.adshelpdesk.repositories.ClienteRepository;
import com.ads.adshelpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired  // ponto de injeção de dependências
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Adson Sa", "330.165.900-42", "adsonconcecao@yahoo.com.br", "123");
		tec1.addPerfis(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Gil pimentel", "866.863.110-14", "gilsel@gmail.com", "1234");
		cli1.addPerfis(Perfil.CLIENTE);

		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1,
				cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
		
	}
}
