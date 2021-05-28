package br.com.zupacademy.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {

	@Id @GeneratedValue
	private Long id;
	@NotNull
	private String link;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	@Deprecated
	public Imagem() {
	}

	public Imagem(@NotNull String link, @NotNull Produto produto) {
		this.link = link;
		this.produto = produto;
	}

	public String getLink() {
		return link;
	}

	public Produto getProduto() {
		return produto;
	}	
	
}
