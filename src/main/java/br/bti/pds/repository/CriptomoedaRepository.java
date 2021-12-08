package br.bti.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bti.pds.model.ParametroCriptomoeda;

@Repository
public interface CriptomoedaRepository extends JpaRepository<ParametroCriptomoeda, Integer> {

}
