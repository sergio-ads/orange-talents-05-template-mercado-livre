package br.com.zupacademy.mercadolivre.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mercadolivre.model.Opiniao;

public class OpiniaoDto {

	private Integer nota;
	private String titulo;
	private String descricao;
	private String usuario;
	private String produto;
	
	public OpiniaoDto(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.usuario = opiniao.getUsuario().getLogin();
		this.produto = opiniao.getProduto().getNome();
	}

	public static List<OpiniaoDto> converter(List<Opiniao> opinioes) {
		return opinioes.stream().map(OpiniaoDto::new).collect(Collectors.toList());
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

}
