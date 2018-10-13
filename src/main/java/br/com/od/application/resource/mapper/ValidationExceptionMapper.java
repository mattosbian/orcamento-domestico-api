package br.com.od.application.resource.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.od.application.service.exception.ValidationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException e) {

		return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	}

}
