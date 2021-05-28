package br.com.zupacademy.mercadolivre.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.Opiniao;
import br.com.zupacademy.mercadolivre.model.dto.OpiniaoDto;
import br.com.zupacademy.mercadolivre.model.request.OpiniaoRequest;
import br.com.zupacademy.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/opiniao")
public class OpiniaoController {
	@Autowired
	private OpiniaoRepository opiniaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<OpiniaoDto> listAll() {
		List<Opiniao> opiniaos = opiniaoRepository.findAll();
		return OpiniaoDto.converter(opiniaos);
	}
	
	@PostMapping
	public OpiniaoDto save(@RequestBody @Valid OpiniaoRequest opiniaoRequest) {
		Opiniao opiniao = opiniaoRequest.toModel(produtoRepository, usuarioRepository);
		opiniaoRepository.save(opiniao);
		return new OpiniaoDto(opiniao);
	}

}
