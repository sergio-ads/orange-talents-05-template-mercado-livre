package br.com.zupacademy.mercadolivre.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mercadolivre.model.Categoria;

public class CategoriaDto {
	private String nome;
	private String categoriaMae;

	public CategoriaDto(Categoria categoria) {
		this.nome = categoria.getNome();
		if(categoria.getCategoriaMae() != null)
			this.categoriaMae = categoria.getCategoriaMae().getNome();
	}

	public String getNome() {
		return nome;
	}

	public String getCategoriaMae() {
		return categoriaMae;
	}

	public static List<CategoriaDto> converter(List<Categoria> categorias) {
		return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}
}
