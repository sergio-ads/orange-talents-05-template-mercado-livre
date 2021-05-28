package br.com.zupacademy.mercadolivre.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.mercadolivre.model.Produto;

public class ProdutoDto {
	private String nome;
	private BigDecimal valor;
	private Long quantidade;
	private Map<String, String> caracteristicas;
	private String descricao;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime dataCadastro;
	
	public ProdutoDto(Produto produto) {		
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.caracteristicas = produto.getCaracteristicas();
		this.descricao = produto.getDescricao();
		this.dataCadastro = produto.getDataCadastro();
	}

	public static List<ProdutoDto> converter(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Map<String, String> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

}
