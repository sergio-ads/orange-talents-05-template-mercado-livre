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

import br.com.zupacademy.mercadolivre.model.Usuario;
import br.com.zupacademy.mercadolivre.model.dto.UsuarioDto;
import br.com.zupacademy.mercadolivre.model.request.UsuarioRequest;
import br.com.zupacademy.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<UsuarioDto> listAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return UsuarioDto.converter(usuarios);
	}
	
	@PostMapping
	public UsuarioDto save(@RequestBody @Valid UsuarioRequest usuarioRequest) {
		Usuario usuario = usuarioRequest.toModel();
		usuarioRepository.save(usuario);
		return new UsuarioDto(usuario);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
