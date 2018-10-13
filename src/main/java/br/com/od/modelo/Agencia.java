package br.com.od.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.od.modelo.transferobject.DadoBancarioTO;


@Entity
@Table(name="TB_AGENCIA")
public class Agencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQ_ID_AGENCIA")
	@SequenceGenerator( sequenceName="SQ_ID_AGENCIA" ,name="SQ_ID_AGENCIA",initialValue=1,allocationSize=1)
	@Column(name="ID_AGENCIA")
	private Long idAgencia;
	
	@NotNull(message="Codigo da agencia deve ser informado")
	@Column(name="CD_AGENCIA")
	private Long codigoAgencia;
	
	@NotNull(message="Um banco deve ser informado")
	@ManyToOne
	@JoinColumn(name="ID_BANCO")
	private Banco banco;
	
	@Column(name="NR_DIGITO")
	private Integer digito;
	
	@OneToMany(mappedBy="agencia",cascade = CascadeType.ALL,orphanRemoval=true)
	private List<Conta> contas;
	
	
	public Agencia() {
	
	}	
	
	
	public Agencia(Long codigoAgencia, Banco banco) {
		super();
		this.codigoAgencia = codigoAgencia;
		this.banco = banco;
	}




	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public Long getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(Long codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}


	public void abrirConta(Cliente cliente,DadoBancarioTO dadoBancario) {
		
		Conta conta = new Conta();
		
		conta.setAgencia(this);
		conta.setCliente(cliente);
		
		conta.setNumeroDigito(dadoBancario.getNumeroDigito());
		conta.setCdTipoConta(dadoBancario.getCdTipoConta());
		
		novaConta(conta);
	}


	private void novaConta(Conta conta) {
		this.contas.add(conta);		
	}
	
	
	
}
