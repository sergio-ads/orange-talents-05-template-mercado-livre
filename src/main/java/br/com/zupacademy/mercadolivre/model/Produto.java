package br.com.zupacademy.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Produto {

	@Id @GeneratedValue
	private Long id;
	@NotBlank
	private String nome;
	@NotNull @Positive
	private BigDecimal valor;
	@NotNull @Min(value = 0)
	private Long quantidade;
	@NotNull @Size(min = 3) @ElementCollection
	private Map<String, String> caracteristicas;
	@NotBlank @Size(max = 1000)
	private String descricao;
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	
	@ManyToOne
	@NotNull
	private Categoria categoria;
	
	@Deprecated
	public Produto() { }

	public Produto(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @Min(0) Long quantidade,
			@NotNull @Size(min = 3) Map<String, String> caracteristica, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristica;
		this.descricao = descricao;
		this.categoria = categoria;
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

	public Categoria getCategoria() {
		return categoria;
	}
	
}
