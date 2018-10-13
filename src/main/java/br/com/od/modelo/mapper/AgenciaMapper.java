package br.com.od.modelo.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.od.modelo.Agencia;
import br.com.od.modelo.transferobject.AgenciaTO;

@Component
public class AgenciaMapper implements Serializable {

     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AgenciaMapper() {
	
	}

	
	
	public List<AgenciaTO> toTransferObject(List<Agencia> agencias) {

		configure();
		             
		List<AgenciaTO> agenciaTOs = new ArrayList<>();
		
		for(Agencia agencia : agencias) {
		    agenciaTOs.add(modelMapper.map(agencia, AgenciaTO.class))	;
		}
		return agenciaTOs;
	}
	
	
	private void configure() {
		modelMapper.addMappings(new PropertyMap<Agencia, AgenciaTO>() {

			@Override
			protected void configure() {
                 map().setIdBanco(source.getBanco().getIdBanco());
				
			}
		});
	
	}



	public Agencia toAgencia(AgenciaTO agenciaTO) {

		return modelMapper.map(agenciaTO, Agencia.class);
	}
	
	
	
}
