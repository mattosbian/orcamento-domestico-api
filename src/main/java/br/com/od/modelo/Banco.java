package br.com.od.modelo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.modelmapper.ModelMapper;

import br.com.od.modelo.transferobject.BancoTO;
import br.com.od.modelo.transferobject.DadoBancarioTO;


@Entity
@Table(name = "TB_BANCO")
public class Banco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ID_BANCO")
	@SequenceGenerator(name = "SQ_ID_BANCO", sequenceName = "SQ_ID_BANCO", initialValue = 1, allocationSize = 1)
	@Column(name = "ID_BANCO")
	private Long idBanco;


	@NotNull(message = "C�digo do banco deve ser informado")
	@Column(name = "CD_BANCO")
	private Long codigoBanco;


	@NotBlank(message = "O nome do banco n�o pode ser branco ou nulo")
	@Column(name = "NM_BANCO")
	private String nomeBanco;

	@OneToMany(mappedBy = "banco",cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Agencia> agencias;

	public Banco() {

	}

	public Banco(Long codigoBanco, String nomeBanco) {
		super();
		this.codigoBanco = codigoBanco;
		this.nomeBanco = nomeBanco;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public Long getCodigoBanco() {
		return codigoBanco;
	}

	
	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBanco == null) ? 0 : idBanco.hashCode());
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
		Banco other = (Banco) obj;
		if (idBanco == null) {
			if (other.idBanco != null)
				return false;
		} else if (!idBanco.equals(other.idBanco))
			return false;
		return true;
	}
	
	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void trocarNome(String nomeBanco) {
		if (nomeBanco == null || nomeBanco.isEmpty()) {
			throw new IllegalArgumentException("Nome banco do não pode ser nulo");
		}
		
		setNomeBanco(nomeBanco);
	}

	
	public void novaAgencia(Agencia agencia) {
		
		agencia.setBanco(this);
		getAgencias().add(agencia);
	}

	public BancoTO toTransferObject() {
		ModelMapper modelMapper = new ModelMapper();
		return  modelMapper.map(this, BancoTO.class);
	}

	public void removerAgencia(Long codigoAgencia) {
		
	   Iterator<Agencia> iterator = getAgencias().iterator();
	    
	    while(iterator.hasNext()) {
	    	Agencia agencia = iterator.next();
	    	if(agencia.getCodigoAgencia().equals(codigoAgencia)) {
	    		iterator.remove();	    		
	    	}	    	
	    }
		
	}

	public void abrirConta(Cliente cliente,DadoBancarioTO dadoBancario) {

		Agencia agencia = buscarAgencia(dadoBancario.getAgenciaTO().getCodigoAgencia());
		agencia.abrirConta(cliente,dadoBancario);
	}

	public boolean existeAgencia(Long codigoAgencia) {
		return buscarAgencia(codigoAgencia)!= null;
		
	}

	private Agencia buscarAgencia(Long codigoAgencia) {
		for(Agencia agencia : agencias) {
			if (agencia.getCodigoAgencia().equals(codigoAgencia)) {
				return agencia;
			}
		}
		return null;
	}
}
