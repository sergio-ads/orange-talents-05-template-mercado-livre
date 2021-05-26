package br.com.zupacademy.mercadolivre.model.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mercadolivre.model.Categoria;
import br.com.zupacademy.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.mercadolivre.validator.UniqueValue;

public class CategoriaRequest {

	@NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	private String categoriaMae;

	public CategoriaRequest(@NotBlank String nome, String categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public Categoria toModel(CategoriaRepository categoriaRepository) {
		Categoria categoria;
		if(categoriaMae != null) {
			Categoria categoriaMaeObjeto = categoriaRepository.findByNome(categoriaMae).get();
			categoria = new Categoria(nome, categoriaMaeObjeto);
		} else {
			categoria = new Categoria(nome, null);
		}
		return categoria;
	}
	
}
