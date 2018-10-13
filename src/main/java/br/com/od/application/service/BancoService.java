package br.com.od.application.service;


import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.od.application.service.exception.ServiceException;
import br.com.od.application.service.exception.ValidationException;
import br.com.od.infra.repository.BancoRepository;
import br.com.od.modelo.Agencia;
import br.com.od.modelo.Banco;
import br.com.od.modelo.Cliente;
import br.com.od.modelo.mapper.AgenciaMapper;
import br.com.od.modelo.mapper.Mapper;
import br.com.od.modelo.transferobject.AgenciaTO;
import br.com.od.modelo.transferobject.BancoTO;
import br.com.od.modelo.transferobject.DadoBancarioTO;

@Service
public class BancoService {

	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private Mapper mapper;

	@Transactional
	public void cadastrarBanco(Banco banco) throws ServiceException {

		if (existeBancoCadastrado(banco.getCodigoBanco())) {
			throw new ServiceException("Banco j� cadastrado");
		}

		try {
			bancoRepository.saveAndFlush(banco);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Transactional
	public void alterarNomeBanco(long codigoBanco, String nomeBanco) throws ServiceException {

		Banco banco = buscarPorCodigo(codigoBanco);

		banco.trocarNome(nomeBanco);
	}

	@Transactional
	public void removerBanco(Long codigoBanco) throws ServiceException {
		try {
			Banco banco = bancoRepository.findBycodigoBanco(codigoBanco);
			bancoRepository.delete(banco);
			bancoRepository.flush();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Transactional(readOnly = true)
	public Banco buscarPorCodigo(Long codigoBanco) throws ValidationException {
		Banco banco = bancoRepository.findBycodigoBanco(codigoBanco);

		verificarSeBancoExiste(banco);

		return banco;
	}

	@Transactional(readOnly = true)
	public Banco buscarPorId(Long idBanco) throws ValidationException {
		Banco banco = bancoRepository.findOne(idBanco);

		verificarSeBancoExiste(banco);

		return banco;
	}

	private void verificarSeBancoExiste(Banco banco) {
		if (banco == null) {
			throw new ValidationException("Banco nao cadastrado");
		}
	}

	
	private boolean existeBancoCadastrado(Long codigoBanco) {
		if (codigoBanco != null) {
			return buscarPorCodigo(codigoBanco) != null;
		}

		return false;
	}

	@Transactional
	public void novaAgencia(Long codigoBanco, Agencia agencia) throws ServiceException ,ValidationException{

		try {
			Banco banco = buscarPorCodigo(codigoBanco);
			banco.novaAgencia(agencia);
			bancoRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new ServiceException("Agencia ja cadastrada",e);
		}catch (ConstraintViolationException e) {
			throw new ValidationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Transactional(readOnly=true)
	public List<Agencia> listarAgencias(Long codigoBanco) {
		
		return buscarPorCodigo(codigoBanco).getAgencias();
	
	}

	@Transactional
	public void removerAgencia(Long codigoBanco, Long codigoAgencia) throws ServiceException {

		 try {
			Banco banco = buscarPorCodigo(codigoBanco);

			banco.removerAgencia(codigoAgencia);
			bancoRepository.flush();

		} catch (Exception e) {
			throw new ServiceException(e.getMessage(),e);
		}
		
	}

	@Transactional
	public void abrirConta(Cliente cliente, DadoBancarioTO dadoBancario) throws ServiceException {
		
		if (dadoBancario == null) {
			throw new ServiceException("Nenhum dado bancário foi informado");
		}
				
		Banco banco = buscarPorId(dadoBancario.getIdBanco());
		
		AgenciaTO agenciaTO = dadoBancario.getAgenciaTO();

		if (!banco.existeAgencia(agenciaTO.getCodigoAgencia())) {
			novaAgencia(dadoBancario.getIdBanco(), mapper.toAgencia(agenciaTO));
		}
		
		banco.abrirConta(cliente,dadoBancario);		
		
		bancoRepository.flush();
	}

}
