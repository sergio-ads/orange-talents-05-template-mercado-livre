package br.com.zupacademy.mercadolivre.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mercadolivre.compartilhado.email.EnviaEmail;
import br.com.zupacademy.mercadolivre.gateway.Gateway;
import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.dto.CompraDto;
import br.com.zupacademy.mercadolivre.model.request.CompraRequest;
import br.com.zupacademy.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/compra")
public class CompraController {
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EnviaEmail enviaEmail;
	
	@GetMapping
	public List<CompraDto> listAll() {
		List<Compra> compras = compraRepository.findAll();
		return CompraDto.converter(compras);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid CompraRequest compraRequest, 
			UriComponentsBuilder uriComponentsBuilder, Principal principal) {
		
		Compra compra = compraRequest.toModel(produtoRepository, usuarioRepository, principal);
		Produto produto = compra.getProduto();
		Gateway gateway = compra.getGateway();
		
		compraRepository.save(compra);
		
		enviaEmail.enviar(produto.getUsuario(), 
				"Estão querendo comprar seu produto: " +produto.getNome() +", quantidade: " +compra.getQuantidade());
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(gateway.getRedirect(compra, uriComponentsBuilder));
	}	
	
}
