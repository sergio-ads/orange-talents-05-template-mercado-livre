package br.com.zupacademy.mercadolivre.model.request;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zupacademy.mercadolivre.model.Categoria;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.mercadolivre.validator.UniqueValue;

public class ProdutoRequest {
	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;
	@NotNull @Positive
	private BigDecimal valor;
	@NotNull @Min(value = 0)
	private Long quantidade;
	@NotNull @Size(min = 3)
	private Map<String, String> caracteristicas;
	@NotBlank @Size(max = 1000)
	private String descricao;
	@NotNull
	private String categoria;
	
	public ProdutoRequest(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @Min(0) Long quantidade,
			@NotNull @Size(min = 3) Map<String, String> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			@NotNull String categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Produto toModel(CategoriaRepository categoriaRepository) {
		Categoria categoriaObjeto = categoriaRepository.findByNome(categoria).get();
		Produto produto = new Produto(nome, valor, quantidade, caracteristicas, descricao, categoriaObjeto);
		return produto;
	}
}