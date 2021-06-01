package br.com.zupacademy.mercadolivre.compartilhado.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscal {
	
	public void processar(String idCompra, Long idComprador) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> request = Map.of("idCompra", idCompra, "idComprador", String.valueOf(idComprador));
		
		restTemplate.postForEntity("http://localhost:8080/nota-fiscal", request, String.class);
	}

}
