package br.com.zupacademy.mercadolivre.gateway.enums;

import br.com.zupacademy.mercadolivre.model.StatusTransacao;

public enum StatusRetornoPaypal {
	SUCESSO(1) {
		@Override
		public StatusTransacao converte() {
			return StatusTransacao.SUCESSO;
		}
	}, ERRO(0) {
		@Override
		public StatusTransacao converte() {
			return StatusTransacao.ERRO;
		}
	};
	
	StatusRetornoPaypal(int status) { 
		this.status = status;
	}

	int status;
	
	public abstract StatusTransacao converte();

}
