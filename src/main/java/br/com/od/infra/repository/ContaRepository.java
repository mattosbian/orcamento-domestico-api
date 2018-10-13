package br.com.od.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.od.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
