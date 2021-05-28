package br.com.zupacademy.mercadolivre.model.request;

import java.security.Principal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.mercadolivre.model.Pergunta;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validator.ExisteUnico;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	@NotNull @ExisteUnico(domainClass = Usuario.class, fieldName = "login")
	private String usuario;
	@NotNull @ExisteUnico(domainClass = Produto.class, fieldName = "nome")
	private String produto;

	public PerguntaRequest(@NotBlank String titulo, @NotNull String usuario, @NotNull String produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Pergunta toModel(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, Principal principal) {
		Produto produtoObjeto = produtoRepository.findByNome(produto).get();
		Usuario UsuarioObjeto = usuarioRepository.findByLogin(principal.getName()).get();
		Pergunta pergunta = new Pergunta(titulo, null, UsuarioObjeto, produtoObjeto);
		return pergunta;
	}

}
