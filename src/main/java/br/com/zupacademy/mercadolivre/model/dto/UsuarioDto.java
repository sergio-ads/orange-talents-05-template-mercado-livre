package br.com.zupacademy.mercadolivre.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.mercadolivre.model.Usuario;

public class UsuarioDto {
	private String login;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime dataCadastro;
	
	public UsuarioDto(Usuario usuario) {
		this.login = usuario.getLogin();
		this.dataCadastro = usuario.getDataCadastro();
	}

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}	
	
	public String getLogin() {
		return login;
	}
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	
}
