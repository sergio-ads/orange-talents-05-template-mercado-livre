package br.com.zupacademy.mercadolivre.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Produto;
import br.com.zupacademy.mercadolivre.model.dto.ProdutoDto;
import br.com.zupacademy.mercadolivre.model.request.ProdutoRequest;
import br.com.zupacademy.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<ProdutoDto> listAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return ProdutoDto.converter(produtos);
	}
	
	@PostMapping
	public ProdutoDto save(@RequestBody @Valid ProdutoRequest produtoRequest) {
		Produto produto = produtoRequest.toModel(categoriaRepository);
		produtoRepository.save(produto);
		return new ProdutoDto(produto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
