package br.com.zupacademy.mercadolivre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mercadolivre.model.Opiniao;

public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {

	List<Opiniao> findByProduto_nome(String produtoNome);

}
