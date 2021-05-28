package br.com.zupacademy.mercadolivre.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.mercadolivre.model.Opiniao;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validator.ExisteUnico;

public class OpiniaoRequest {

	@NotNull
	@Min(1) @Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank @Size(max = 500)
	private String descricao;
	
	@NotNull
	@ExisteUnico(domainClass = Usuario.class, fieldName = "login")
	private String usuario;
	@NotNull
	@ExisteUnico(domainClass = Produto.class, fieldName = "nome")
	private String produto;
	
	public OpiniaoRequest(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull String usuario, @NotNull String produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Opiniao toModel(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
		Produto produtoObjeto = produtoRepository.findByNome(produto).get();
		Usuario UsuarioObjeto = usuarioRepository.findByLogin(usuario).get();
		Opiniao opiniao = new Opiniao(nota, titulo, descricao, UsuarioObjeto, produtoObjeto);
		return opiniao;
	}

}
