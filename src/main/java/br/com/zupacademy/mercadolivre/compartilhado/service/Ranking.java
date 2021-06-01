package br.com.zupacademy.mercadolivre.compartilhado.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Ranking {
	
	public void processar(String idCompra, Long idVendedor) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> request = Map.of("idCompra", idCompra, "idVendedor", String.valueOf(idVendedor));
		
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
	}

}
