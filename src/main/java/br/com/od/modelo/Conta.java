package br.com.od.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_CONTA")
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQ_CD_CONTA")
	@SequenceGenerator( sequenceName="SQ_CD_CONTA" ,name="SQ_CD_CONTA",initialValue=1,allocationSize=1)
	@Column(name="CD_CONTA")	
	private Long cdConta;
	
	@NotNull(message="Uma agencia deve ser informada")
	@ManyToOne
	@JoinColumn(name="ID_AGENCIA")
	private Agencia agencia;
	
	@NotNull(message="O tipo de conta deve ser informado")
	@Column(name="cd_tipo_conta")	
	private Long cdTipoConta;
	

	@NotNull(message="Numero e Digito deve ser informado")
	@Column(name="NR_DIGITO_CONTA")
	private Long numeroDigito;

	@OneToOne	
	@JoinColumn(name="ID_CLIENTE")
	private Cliente cliente;

	public Long getCdConta() {
		return cdConta;
	}


	public void setCdConta(Long cdConta) {
		this.cdConta = cdConta;
	}


	public Agencia getAgencia() {
		return agencia;
	}


	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}




	public Long getCdTipoConta() {
		return cdTipoConta;
	}


	public void setCdTipoConta(Long cdTipoConta) {
		this.cdTipoConta = cdTipoConta;
	}


	public Long getNumeroDigito() {
		return numeroDigito;
	}


	public void setNumeroDigito(Long numeroDigito) {
		this.numeroDigito = numeroDigito;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdConta == null) ? 0 : cdConta.hashCode());
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
		Conta other = (Conta) obj;
		if (cdConta == null) {
			if (other.cdConta != null)
				return false;
		} else if (!cdConta.equals(other.cdConta))
			return false;
		return true;
	}

	
	
	
	
}
