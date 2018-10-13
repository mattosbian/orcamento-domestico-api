package br.com.od.infra.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.od.application.resource.BancoResource;
import br.com.od.application.resource.mapper.ServiceExceptionMapper;

@ApplicationPath("/resource")
public class RestConfig  extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		return new HashSet<Class<?>>(
		          Arrays.asList(
		                  BancoResource.class,
		                  ServiceExceptionMapper.class/*,
		                  JWTSecurityFilter.class*/
		                  ));
		          
	}

}
