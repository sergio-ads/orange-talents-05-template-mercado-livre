package br.com.zupacademy.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank @Email
	private String login;
	@NotBlank @Size(min = 6)
	private String senha;
	@NotNull @PastOrPresent
	private LocalDateTime dataCadastro;

	@Deprecated
	public Usuario() { }
	
	public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha,
			@NotNull @PastOrPresent LocalDateTime dataCadastro) {
		this.login = login;
		this.senha = Base64.getEncoder().encodeToString(senha.getBytes());
		this.dataCadastro = dataCadastro;
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
