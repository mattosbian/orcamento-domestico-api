package br.com.od.application.service;

import java.util.List;

import br.com.od.application.service.exception.ServiceException;
import br.com.od.modelo.Conta;
import br.com.od.modelo.TipoConta;

public interface ContaService {

	
	void novaConta(Conta conta) throws ServiceException;
	
	Conta buscarConta(long cdConta);
	
	List<TipoConta> listarTipoConta();
	
}
