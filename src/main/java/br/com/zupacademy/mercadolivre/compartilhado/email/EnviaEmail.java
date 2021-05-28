package br.com.zupacademy.mercadolivre.compartilhado.email;

import br.com.zupacademy.mercadolivre.model.Usuario;

public interface EnviaEmail {
	
	public void enviar(Usuario usuario, String mensagem);

}
