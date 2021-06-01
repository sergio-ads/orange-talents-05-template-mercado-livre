package br.com.zupacademy.mercadolivre.gateway;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mercadolivre.model.Compra;

public enum Gateway {
	
	PAGSEGURO {
		@Override
		public String getRedirect(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/returnId=" + compra.getId() + "?redirectUrl="
					+ urlRetornoPagseguro;
		}
	},
	PAYPAL {
		@Override
		public String getRedirect(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/buyerId=" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	abstract public String getRedirect(Compra compra, UriComponentsBuilder uriComponentsBuilder);
	
}