package br.com.zupacademy.mercadolivre.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.mercadolivre.gateway.Gateway;

@Entity
public class Compra {

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@NotNull @Positive
	private Long quantidade;
	private BigDecimal valor; // Valor do produto naquele momento
	@NotNull
	@ManyToOne
	private Produto produto;
	@NotNull
	@ManyToOne
	private Usuario comprador;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gateway gateway;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusCompra status;
	
	@Deprecated
	public Compra() { }

	public Compra(@NotNull @Positive Long quantidade, BigDecimal valor, @NotNull Produto produto,
			@NotNull Usuario comprador, @NotNull Gateway gateway) {
		this.quantidade = quantidade;
		this.valor = valor;
		this.produto = produto;
		this.comprador = comprador;
		this.gateway = gateway;
		this.status = StatusCompra.INICIADO;
	}

	public String getId() {
		return id;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public StatusCompra getStatus() {
		return status;
	}

	
}
