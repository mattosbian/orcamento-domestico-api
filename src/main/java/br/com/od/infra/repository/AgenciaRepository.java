package br.com.od.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.od.modelo.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
	
	
	@Query("Select t From Agencia t where t.codigoAgencia=:codigoAgencia and t.banco.idBanco = :idBanco")
	Agencia findByCodigoAgenciaAndIdBanco(Long codigoAgencia,Long idBanco);

}
