package br.com.od.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.od.application.service.exception.ServiceException;
import br.com.od.infra.repository.ContaRepository;
import br.com.od.infra.repository.TipoContaRepository;
import br.com.od.modelo.Conta;
import br.com.od.modelo.TipoConta;

@Service
public class ContaBean implements ContaService {

	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TipoContaRepository tipoContaRepository;
	
	@Transactional
	public void novaConta(Conta conta) throws ServiceException {
		try {
			contaRepository.saveAndFlush(conta);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Transactional(readOnly=true)
	public Conta buscarConta(long cdConta) {
		return contaRepository.findOne(cdConta);
	}


	@Transactional(readOnly=true)
	public List<TipoConta> listarTipoConta(){
		return tipoContaRepository.findAll();
	}
	
		
}
