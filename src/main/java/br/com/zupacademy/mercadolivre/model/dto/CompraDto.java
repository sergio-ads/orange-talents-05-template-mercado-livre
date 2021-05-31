package br.com.zupacademy.mercadolivre.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mercadolivre.gateway.Gateway;
import br.com.zupacademy.mercadolivre.model.Compra;

public class CompraDto {

	private String identificador;
	private Long quantidade;
	private BigDecimal valor;
	private String produto;
	private String comprador;
	private Gateway gateway;

	public CompraDto(Compra compra) {
		this.identificador = compra.getId();
		this.quantidade = compra.getQuantidade();
		this.valor = compra.getValor();
		this.produto = compra.getProduto().getNome();
		this.comprador = compra.getComprador().getLogin();
		this.gateway = compra.getGateway();
	}

	public static List<CompraDto> converter(List<Compra> compras) {
		return compras.stream().map(CompraDto::new).collect(Collectors.toList());
	}

	public String getIdentificador() {
		return identificador;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getProduto() {
		return produto;
	}

	public String getComprador() {
		return comprador;
	}

	public Gateway getGateway() {
		return gateway;
	}

}
