package br.com.zupacademy.mercadolivre.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mercadolivre.model.Imagem;

public class ImagemDto {
	private String link;

	public ImagemDto(Imagem imagem) {
		this.link = imagem.getLink();
	}

	public String getLink() {
		return link;
	}

	public static List<ImagemDto> converter(List<Imagem> imagens) {
		return imagens.stream().map(ImagemDto::new).collect(Collectors.toList());
	}

}
