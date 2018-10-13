package br.com.od.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.od.application.service.exception.ServiceException;
import br.com.od.infra.repository.ClienteRepository;
import br.com.od.modelo.Cliente;
import br.com.od.modelo.mapper.Mapper;
import br.com.od.modelo.transferobject.ClienteTO;


@Service
public class ClienteService {

	@Autowired
	private Mapper mapper;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private BancoService bancoService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void novoCliente(ClienteTO clienteTO) throws ServiceException{
		
		Cliente cliente = mapper.toCliente(clienteTO);

		cliente = clienteRepository.saveAndFlush(cliente);
		
		bancoService.abrirConta(cliente,clienteTO.getDadoBancario());
		
	}
	
}
