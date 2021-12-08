package br.bti.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.bti.pds.model.ParametroAtivo;

public interface ParametroAtivoRepository extends JpaRepository<ParametroAtivo, Integer> {

	@Query(value = "SELECT * FROM parametro_acao", nativeQuery = true)
	public List<ParametroAtivo> findAllNaoMapeados();

}
