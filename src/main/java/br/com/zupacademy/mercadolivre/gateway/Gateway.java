package br.com.zupacademy.mercadolivre.gateway;

public enum Gateway {
	PAGSEGURO,
	PAYPAL;

	public String getRedirect(String idCompra, String urlRetorno) {
		switch(this.name()) {
			case "PAGSEGURO": {
				return "pagseguro.com?returnId=" +idCompra +"&redirectUrl=" +urlRetorno;
			}
			case "PAYPAL": {
				return "paypal.com?buyerId=" +idCompra +"&redirectUrl=" +urlRetorno;
			}
		}
		return "";
	}
	
}