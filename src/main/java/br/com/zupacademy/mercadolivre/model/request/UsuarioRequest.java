package br.com.zupacademy.mercadolivre.model.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.mercadolivre.model.Usuario;

public class UsuarioRequest {
	@NotBlank @Email
	private String login;
	@NotBlank @Size(min = 6)
	private String senha;
	private LocalDateTime dataCadastro;

	public Usuario toModel() {
		Usuario usuario = new Usuario(login, senha, dataCadastro);
		return usuario;
	}
	
	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
		this.dataCadastro = LocalDateTime.now();
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

}
