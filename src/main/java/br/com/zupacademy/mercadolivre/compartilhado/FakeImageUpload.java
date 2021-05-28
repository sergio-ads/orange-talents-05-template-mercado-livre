package br.com.zupacademy.mercadolivre.compartilhado;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile("dev")
public class FakeImageUpload implements ImageUpload {

	public List<String> enviar(List<MultipartFile> imagens) {
		return imagens.stream()
				.map(imagem -> "https://www.fakeimage.com.br/" +UUID.randomUUID().toString() +"_" +imagem.getOriginalFilename())
				.collect(Collectors.toList());
	}
	
}
