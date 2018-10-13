package br.com.od.modelo.transferobject;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="banco")
public class BancoTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Long codigoBanco;

	private String nomeBanco;

	public Long getCodigoBanco() {
		return codigoBanco;
	}

	@XmlElement
	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	@XmlElement
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	
	
}
