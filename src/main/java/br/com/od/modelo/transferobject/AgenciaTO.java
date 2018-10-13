package br.com.od.modelo.transferobject;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Agencia")
public class AgenciaTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codigoAgencia;
	
    private Long idBanco;	

	private Integer digito;
	
	public AgenciaTO() {
		
	}


	public Long getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(Long codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}
	
	

	
	
	
}
