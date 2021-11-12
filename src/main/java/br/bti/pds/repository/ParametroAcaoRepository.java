package br.bti.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.bti.pds.model.ParametroAcao;

public interface ParametroAcaoRepository extends JpaRepository<ParametroAcao, Integer> {

	@Query(value = "SELECT * FROM parametro_acao", nativeQuery = true)
	public List<ParametroAcao> findAllNaoMapeados();

}
