package br.com.zupacademy.mercadolivre.compartilhado;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUpload {
	
	public List<String> enviar(List<MultipartFile> imagens);
	
}
