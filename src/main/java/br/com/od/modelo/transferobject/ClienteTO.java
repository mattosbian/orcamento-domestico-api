package br.com.od.modelo.transferobject;

import java.io.Serializable;

public class ClienteTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpf;
	
	private CredencialTO credencial;

	private DadoBancarioTO dadoBancario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public DadoBancarioTO getDadoBancario() {
		return dadoBancario;
	}

	public void setDadoBancario(DadoBancarioTO dadoBancario) {
		this.dadoBancario = dadoBancario;
	}

	public CredencialTO getCredencial() {
		return credencial;
	}

	public void setCredencial(CredencialTO credencial) {
		this.credencial = credencial;
	}

	
	
	
	

}
