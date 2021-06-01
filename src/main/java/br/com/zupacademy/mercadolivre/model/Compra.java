package br.com.zupacademy.mercadolivre.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.GenericGenerator;

import br.com.zupacademy.mercadolivre.gateway.Gateway;
import br.com.zupacademy.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.mercadolivre.repository.TransacaoRepository;
import io.jsonwebtoken.lang.Assert;

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
	@OneToMany(mappedBy = "compra")
	private List<Transacao> transacoes;
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

	/**
	 * Verifica se a Compra já foi finalizada.
	 * Se não, salva a Transação e adiciona essa Transação na Compra. 
	 * Se a Transação ocorreu com sucesso, então finaliza a Compra.
	 * Salva a Compra.
	 * 
	 * Lança IllegalStateException caso a Compra já tenha sido finalizada.
	 */
	public void adicionaTransacao(Transacao transacao, TransacaoRepository transacaoRepository, CompraRepository compraRepository) {
		Assert.state(!this.status.equals(StatusCompra.FINALIZADO), "Essa compra já foi finalizada");
		transacaoRepository.save(transacao);
		this.transacoes.add(transacao);		
		
		if(transacao.getStatus().equals(StatusTransacao.SUCESSO)) {
			this.status = StatusCompra.FINALIZADO;
		}
		compraRepository.save(this);
	}

	
}
