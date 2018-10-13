package br.com.od.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.od.modelo.TipoConta;

public interface TipoContaRepository extends JpaRepository<TipoConta, Long> {

}
