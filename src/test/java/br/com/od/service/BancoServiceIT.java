package br.com.od.service;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.od.application.service.BancoService;
import br.com.od.application.service.exception.ServiceException;
import br.com.od.application.service.exception.ValidationException;
import br.com.od.infra.config.SpringConfig;
import br.com.od.modelo.Agencia;
import br.com.od.modelo.Banco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
public class BancoServiceIT {

	@Autowired
	private BancoService bancoService;

	private static String targetUrl = "http://localhost:8081/orcamento-domestico/resource";

	@Ignore
	@Test(expected = ServiceException.class)
	public void testaCodigoDoBancoNulo() {

		Banco banco = new Banco(null, "Bradesco");
		bancoService.cadastrarBanco(banco);
	}

	@Ignore
	@Test(expected = ServiceException.class)
	public void testaNomeBancoBranco() {

		Banco banco = new Banco(new Long(237), "");
		bancoService.cadastrarBanco(banco);
	}

	@Ignore
	@Test
	public void t() {

		Banco banco = null;

		ClientConfig clientConfig = new ClientConfig();

		Logger logger = Logger.getLogger(BancoServiceIT.class.getSimpleName());

		LoggingFeature loggingFeature = new LoggingFeature(logger, Level.INFO, null, null);
		clientConfig.register(loggingFeature);
		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(targetUrl);

		banco = webTarget.path("/banco/237").request().get(Banco.class);

		assertEquals("Bradesco", banco.getNomeBanco());

	}

	
	@Test(expected = ValidationException.class)
	public void testaNovaAgencia() throws ValidationException {

		Agencia agencia = new Agencia();
		agencia.setCodigoAgencia(new Long(138));
		agencia.setDigito(4);
		bancoService.novaAgencia(new Long(237), agencia);
		
	}

	@Test	
	public void testaRemocaoAgencia()  {
		Long codigoBanco = new Long(237L);
		Long codigoAgencia = new Long(138);
		bancoService.removerAgencia(codigoBanco,codigoAgencia);
	}
	
}
