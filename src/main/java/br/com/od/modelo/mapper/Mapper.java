package br.com.od.modelo.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.od.modelo.Agencia;
import br.com.od.modelo.Banco;
import br.com.od.modelo.Cliente;
import br.com.od.modelo.Credencial;
import br.com.od.modelo.transferobject.AgenciaTO;
import br.com.od.modelo.transferobject.BancoTO;
import br.com.od.modelo.transferobject.ClienteTO;

@Component
public class Mapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AgenciaMapper agenciaMapper;
	
	public Mapper() {
	
	}

	public Cliente toCliente(ClienteTO clienteTO) {
		
		Cliente cliente = modelMapper.map(clienteTO, Cliente.class);
		if (clienteTO.getCredencial()!= null) {
			Credencial credencial = modelMapper.map(clienteTO.getCredencial(), Credencial.class);
			cliente.setCredencial(credencial);
		}
		return cliente;
	}

	public Agencia toAgencia(AgenciaTO agenciaTO) {
		
		return agenciaMapper.toAgencia(agenciaTO);
	}

	public Banco toBanco(BancoTO bancoTO) {
		
		return modelMapper.map(bancoTO, Banco.class);
	}

	public List<AgenciaTO> toAgencias(List<Agencia> agencias) {

		return agenciaMapper.toTransferObject(agencias);
	}
	
}
