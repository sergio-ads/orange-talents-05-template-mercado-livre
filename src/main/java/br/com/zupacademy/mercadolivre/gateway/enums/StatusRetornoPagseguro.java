package br.com.zupacademy.mercadolivre.gateway.enums;

import br.com.zupacademy.mercadolivre.model.StatusTransacao;

public enum StatusRetornoPagseguro {
	SUCESSO {
		@Override
		public StatusTransacao converte() {
			return StatusTransacao.SUCESSO;
		}
	}, ERRO {
		@Override
		public StatusTransacao converte() {
			return StatusTransacao.ERRO;
		}
	};
	
	public abstract StatusTransacao converte();
}
