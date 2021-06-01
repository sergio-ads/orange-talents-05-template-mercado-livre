package br.com.zupacademy.mercadolivre.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mercadolivre.model.request.NotaFiscalRequest;
import br.com.zupacademy.mercadolivre.model.request.RankingRequest;

@RestController
public class OutrosSistemasController {
	
	@PostMapping(value = "/nota-fiscal")
	public void notaFiscal(@RequestBody NotaFiscalRequest notaFiscalRequest) {
		System.out.println("Nota fiscal criada: idCompra: " +notaFiscalRequest.getIdCompra() 
			+", idComprador: " +notaFiscalRequest.getIdComprador());
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(@RequestBody RankingRequest rankingRequest) {
		System.out.println("Ranking verificado: idCompra: " +rankingRequest.getIdCompra() 
			+", idVendedor: " +rankingRequest.getIdVendedor());
	}

}
