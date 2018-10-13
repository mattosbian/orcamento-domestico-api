package br.com.od.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.od.modelo.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long>{

	Banco findBycodigoBanco(Long codigoBanco);
	
}
