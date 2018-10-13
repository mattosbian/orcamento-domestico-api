package br.com.od.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.od.application.service.ClienteService;
import br.com.od.infra.config.SpringConfig;
import br.com.od.modelo.transferobject.AgenciaTO;
import br.com.od.modelo.transferobject.ClienteTO;
import br.com.od.modelo.transferobject.CredencialTO;
import br.com.od.modelo.transferobject.DadoBancarioTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
public class ClienteServiceIT {

	@Autowired
	private ClienteService clienteService;
	
	
	@Test
	public void testaNovoCliente() {
		
		ClienteTO clienteTO = new ClienteTO();
		
		clienteTO.setCpf("30195739809");
		clienteTO.setNome("Everton Mattos Bianchini");
		
		CredencialTO credencialTO = new CredencialTO();
		credencialTO.setLogin("mattosbian");
		credencialTO.setSenha("java0911");
		
		clienteTO.setCredencial(credencialTO);
		
		
		DadoBancarioTO dadoBancario = new DadoBancarioTO();
		AgenciaTO agenciaTO = new AgenciaTO();
		agenciaTO.setCodigoAgencia(new Long(1048));
		agenciaTO.setIdBanco(new Long(21));
		dadoBancario.setAgenciaTO(agenciaTO);
		dadoBancario.setNumeroDigito(new Long(1444387));
		
		
		clienteTO.setDadoBancario(dadoBancario);
		
		clienteService.novoCliente(clienteTO);
	}
	
}
