package br.com.zupacademy.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Pergunta {

	@Id @GeneratedValue
	private Long id;
	@NotBlank
	private String titulo;
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	@NotNull
	private Usuario usuario;
	@ManyToOne
	@NotNull
	private Produto produto;
	
	public Pergunta(@NotBlank String titulo, LocalDateTime dataCriacao, @NotNull Usuario usuario,
			@NotNull Produto produto) {
		this.titulo = titulo;
		this.dataCriacao = dataCriacao;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Deprecated
	public Pergunta() { }

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
}
