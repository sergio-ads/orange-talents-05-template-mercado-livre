package br.com.zupacademy.mercadolivre.compartilhado.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mercadolivre.model.Usuario;

@Component
@Profile("prod")
public class EnviaEmailReal implements EnviaEmail {
	
	@Override
	public void enviar(Usuario usuario, String mensagem) {
		// Implementar o código		
	}
	
}
