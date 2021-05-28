package br.com.zupacademy.mercadolivre.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.mercadolivre.model.Pergunta;

public class PerguntaDto {
	private String titulo;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime dataCriacao;
	private String usuario;
	
	public PerguntaDto(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.dataCriacao = pergunta.getDataCriacao();
		this.usuario = pergunta.getUsuario().getLogin();
	}

	public static List<PerguntaDto> converter(List<Pergunta> opinioes) {
		return opinioes.stream().map(PerguntaDto::new).collect(Collectors.toList());
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getUsuario() {
		return usuario;
	}

}
