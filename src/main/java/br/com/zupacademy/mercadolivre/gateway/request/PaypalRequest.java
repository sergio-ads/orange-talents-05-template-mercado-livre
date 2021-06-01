package br.com.zupacademy.mercadolivre.gateway.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.mercadolivre.gateway.enums.StatusRetornoPaypal;
import br.com.zupacademy.mercadolivre.model.Compra;
import br.com.zupacademy.mercadolivre.model.Transacao;

public class PaypalRequest implements GatewayRequest {

	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPaypal status;
	
	public PaypalRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPaypal status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public Transacao toModel(Compra compra) {
		Transacao transacao = new Transacao(idTransacao, compra, status.converte());
		return transacao;
	}
	
}
