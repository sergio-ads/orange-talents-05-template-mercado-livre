package br.com.zupacademy.mercadolivre.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mercadolivre.compartilhado.image.ImageUpload;
import br.com.zupacademy.mercadolivre.model.Imagem;
import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.ImagemDto;
import br.com.zupacademy.mercadolivre.model.dto.ProdutoDto;
import br.com.zupacademy.mercadolivre.model.request.ImagemRequest;
import br.com.zupacademy.mercadolivre.model.request.ProdutoRequest;
import br.com.zupacademy.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.mercadolivre.repository.ImagemRepository;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ImagemRepository imagemRepository;
	@Autowired
	private ImageUpload fakeImageUpload;
	
	@GetMapping
	public List<ProdutoDto> listAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return ProdutoDto.converter(produtos);
	}
	
	@PostMapping
	public ProdutoDto save(@RequestBody @Valid ProdutoRequest produtoRequest) {
		Produto produto = produtoRequest.toModel(categoriaRepository, usuarioRepository);
		produtoRepository.save(produto);
		return new ProdutoDto(produto);
	}
	
	@PostMapping(value = "/{produto}/imagem")
	public List<ImagemDto> save(@PathVariable String produto, @Valid ImagemRequest imagemRequest, Principal principal) {
		Usuario usuarioLogado = (Usuario) usuarioRepository.findByLogin(principal.getName()).get();
		Produto produtoObjeto = produtoRepository.findByNome(produto).get();
		if(!produtoObjeto.pertenceAoUsuario(usuarioLogado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Este produto não pertence ao usuário autenticado");
		}
		
		List<String> links = fakeImageUpload.enviar(imagemRequest.getImagens());		
		List<Imagem> imagens = produtoObjeto.associaImagens(links);
		imagemRepository.saveAll(imagens);
		return ImagemDto.converter(imagens);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
