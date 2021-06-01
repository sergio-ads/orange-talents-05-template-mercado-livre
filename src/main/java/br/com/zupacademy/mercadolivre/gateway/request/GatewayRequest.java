package br.com.zupacademy.mercadolivre.gateway.request;

import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.Transacao;

public interface GatewayRequest {

	public Transacao toModel(Compra compra);
	
}
