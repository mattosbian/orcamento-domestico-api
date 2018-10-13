package br.com.od.modelo.transferobject;

import java.io.Serializable;

public class DadoBancarioTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AgenciaTO agenciaTO;
	
	private long cdTipoConta = 1L;
	
	private Long numeroDigito;	
	

	public DadoBancarioTO() {
	}

	public AgenciaTO getAgenciaTO() {
		return agenciaTO;
	}

	public void setAgenciaTO(AgenciaTO agenciaTO) {
		this.agenciaTO = agenciaTO;
	}

	public Long getNumeroDigito() {
		return numeroDigito;
	}

	public void setNumeroDigito(Long numeroDigito) {
		this.numeroDigito = numeroDigito;
	}
	
	public Long getIdBanco() {
		return this.agenciaTO.getIdBanco();
	}
	
	public long getCdTipoConta() {
		return cdTipoConta;
	}
	
}