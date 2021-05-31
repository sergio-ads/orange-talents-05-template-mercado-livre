package br.com.zupacademy.mercadolivre.model.request;

import java.security.Principal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.mercadolivre.gateway.Gateway;
import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.mercadolivre.validator.EnumString;
import br.com.zupacademy.mercadolivre.validator.ExisteUnico;

public class CompraRequest {

	@NotNull @Positive
	private Long quantidade;
	@NotBlank
	@ExisteUnico(domainClass = Produto.class, fieldName = "nome")
	private String produto;
	@NotBlank
	@EnumString(domainClass = Gateway.class)
	private String gateway;
	
	public CompraRequest(@NotNull @Positive Long quantidade, @NotBlank String produto, @NotBlank String gateway) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.gateway = gateway;
	}

	public Compra toModel(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, Principal principal) {
		Produto produtoObjeto = produtoRepository.findByNome(produto).get();
		Usuario compradorObjeto = usuarioRepository.findByLogin(principal.getName()).get();
		
		if(produtoObjeto.abateEstoque(quantidade))
			return new Compra(quantidade, produtoObjeto.getValor(), produtoObjeto, compradorObjeto, Gateway.valueOf(gateway));
		else
			throw new IllegalStateException("A quantidade do produto deve ser menor que o estoque: " +quantidade);		
	}

	
}
