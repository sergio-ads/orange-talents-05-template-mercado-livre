package br.com.zupacademy.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mercadolivre.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Optional<Produto> findByNome(String nome);

}
