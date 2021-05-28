package br.com.zupacademy.mercadolivre.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.compartilhado.email.EnviaEmail;
import br.com.zupacademy.mercadolivre.model.Pergunta;
import br.com.zupacademy.mercadolivre.model.dto.PerguntaDto;
import br.com.zupacademy.mercadolivre.model.request.PerguntaRequest;
import br.com.zupacademy.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/pergunta")
public class PerguntaController {
	@Autowired
	private PerguntaRepository perguntaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EnviaEmail enviaEmail;
	
	@GetMapping
	public List<PerguntaDto> listAll() {
		List<Pergunta> perguntas = perguntaRepository.findAll();
		return PerguntaDto.converter(perguntas);
	}
	
	@PostMapping
	public PerguntaDto save(@RequestBody @Valid PerguntaRequest perguntaRequest, Principal principal) {
		Pergunta pergunta = perguntaRequest.toModel(produtoRepository, usuarioRepository, principal);
		perguntaRepository.save(pergunta);
		enviaEmail.enviar(pergunta.getUsuario(), pergunta.getTitulo());
		return new PerguntaDto(pergunta);
	}

}
