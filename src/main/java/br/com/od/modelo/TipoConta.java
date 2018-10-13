package br.com.od.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_TIPO_CONTA")
public class TipoConta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CD_TIPO_CONTA")
	private Long cdTipoConta;
	
	@Column(name="DS_TIPO_CONTA")
	private String dsTipoConta;

	public Long getCdTipoConta() {
		return cdTipoConta;
	}

	public void setCdTipoConta(Long cdTipoConta) {
		this.cdTipoConta = cdTipoConta;
	}

	public String getDsTipoConta() {
		return dsTipoConta;
	}

	public void setDsTipoConta(String dsTipoConta) {
		this.dsTipoConta = dsTipoConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdTipoConta == null) ? 0 : cdTipoConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoConta other = (TipoConta) obj;
		if (cdTipoConta == null) {
			if (other.cdTipoConta != null)
				return false;
		} else if (!cdTipoConta.equals(other.cdTipoConta))
			return false;
		return true;
	}

	
	
	
}
