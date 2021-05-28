package br.com.zupacademy.mercadolivre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.mercadolivre.model.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
	
	Optional<Imagem> findByLink(String link);

	List<Imagem> findByProduto_nome(String produtoNome);

}
