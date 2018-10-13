package br.com.od.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.od.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
