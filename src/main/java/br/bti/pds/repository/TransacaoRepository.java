package br.bti.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bti.pds.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

	public List<Transacao> findAllBySimbolo(String simbolo);
}
