package br.com.zupacademy.mercadolivre.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemRequest {
	@NotNull @Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();
	
	public ImagemRequest(@NotNull @Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

}
