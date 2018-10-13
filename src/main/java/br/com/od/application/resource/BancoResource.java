package br.com.od.application.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.od.application.service.BancoService;
import br.com.od.application.service.exception.ServiceException;
import br.com.od.modelo.Agencia;
import br.com.od.modelo.Banco;
import br.com.od.modelo.mapper.Mapper;
import br.com.od.modelo.transferobject.AgenciaTO;
import br.com.od.modelo.transferobject.BancoTO;



@Service
@Path("banco")
public class BancoResource   {

	@Autowired	
	private BancoService bancoService;
	
	@Autowired
	private Mapper mapper;

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response cadastrarBanco(BancoTO bancoTO) throws ServiceException {
		
	
		Banco banco = mapper.toBanco(bancoTO);
		
		bancoService.cadastrarBanco(banco);
		
		URI uri = URI.create("/banco/"+banco.getIdBanco());
		return Response.created(uri).build();
	}

	
	@Path("{id}/nome")
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	public Response atualizarNome(BancoTO bancoTO) throws ServiceException {
		bancoService.alterarNomeBanco(bancoTO.getCodigoBanco(),bancoTO.getNomeBanco());
		return Response.ok().build();	
		
	}
	
	@Path("{id}")
    @DELETE
	public Response removerBanco(@PathParam("id") Long codigoBanco) throws ServiceException {
		
		bancoService.removerBanco(codigoBanco);
		
		return Response.ok().build();
		
	}

	@Path("{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public BancoTO buscarPorCodigo(@PathParam("id") Long codigoBanco) {	
		
		Banco banco = bancoService.buscarPorCodigo(codigoBanco);
			
		return banco.toTransferObject();
	}

	@Path("{id}/agencia")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAgencia(Agencia agencia, @PathParam("id") Long codigoBanco) {
		bancoService.novaAgencia(codigoBanco,agencia);
		
		URI uri = URI.create("/banco/"+codigoBanco+"/agencia/"+agencia.getCodigoAgencia());
		return Response.created(uri).build();
	}
	
	
	@Path("{id}/agencia")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AgenciaTO> listarAgencias( @PathParam("id") Long codigoBanco) {
		return  mapper.toAgencias(bancoService.listarAgencias(codigoBanco));
	}
	
	
	@Path("{id}/agencia/{codigoAgencia}")
    @DELETE
	public Response removerBanco(@PathParam("id") Long codigoBanco, @PathParam("codigoAgencia") Long codigoAgencia) throws ServiceException {
		
		bancoService.removerAgencia(codigoBanco,codigoAgencia);
		
		return Response.ok().build();
		
	}
	
}
