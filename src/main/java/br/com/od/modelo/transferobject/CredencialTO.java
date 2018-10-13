package br.com.od.modelo.transferobject;

import java.io.Serializable;

public class CredencialTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String login;
	public String senha;

	public CredencialTO() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}