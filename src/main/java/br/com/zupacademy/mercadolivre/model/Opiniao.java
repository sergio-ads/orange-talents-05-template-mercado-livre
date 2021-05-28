package br.com.zupacademy.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opiniao {

	@Id @GeneratedValue
	private Long id;
	@NotNull @Min(1) @Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank @Size(max = 500)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	@NotNull
	@ManyToOne
	private Produto produto;
	
	public Opiniao(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@NotNull Usuario usuario, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	@Deprecated
	public Opiniao() { }

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
}
