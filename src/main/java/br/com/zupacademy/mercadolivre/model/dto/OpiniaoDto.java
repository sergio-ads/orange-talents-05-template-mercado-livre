package br.com.zupacademy.mercadolivre.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mercadolivre.model.Opiniao;

public class OpiniaoDto {

	private Integer nota;
	private String titulo;
	private String descricao;
	private String usuario;
	
	public OpiniaoDto(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.usuario = opiniao.getUsuario().getLogin();
	}

	public static List<OpiniaoDto> converter(List<Opiniao> opinioes) {
		return opinioes.stream().map(OpiniaoDto::new).collect(Collectors.toList());
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUsuario() {
		return usuario;
	}

}
