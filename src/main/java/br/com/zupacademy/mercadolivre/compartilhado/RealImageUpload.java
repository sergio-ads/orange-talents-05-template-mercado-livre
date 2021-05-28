package br.com.zupacademy.mercadolivre.compartilhado;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile("prod")
public class RealImageUpload implements ImageUpload {

	public List<String> enviar(List<MultipartFile> imagens) {
		// Enviar a imagem real
		return null;
	}
	
}
