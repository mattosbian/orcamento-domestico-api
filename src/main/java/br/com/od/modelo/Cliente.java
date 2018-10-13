package br.com.od.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ID_CLIENTE")
	@SequenceGenerator(name = "SQ_ID_CLIENTE", sequenceName = "SQ_ID_CLIENTE", initialValue = 1, allocationSize = 1)
	@Column(name="ID_CLIENTE")
	private Long idCliente;
	
	@NotNull(message="O nome deve ser informado")
	@Length(message="Nome informado ultrapassa o limite de 100 caracteres")
	@Column(name="NM_CLIENTE")
	private String nome;

	@Column(name="nr_cpf")
	private String cpf;
	
	@OneToOne(mappedBy="cliente")
	private Conta conta;

	@NotNull(message="As credencias devem ser informadas")
	@OneToOne(mappedBy="cliente",cascade=CascadeType.ALL)
	private Credencial credencial;
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Conta getConta() {
		return conta;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
		credencial.setCliente(this);
	}
	
		
}
