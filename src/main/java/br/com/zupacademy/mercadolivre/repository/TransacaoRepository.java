package br.com.zupacademy.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mercadolivre.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {

}
