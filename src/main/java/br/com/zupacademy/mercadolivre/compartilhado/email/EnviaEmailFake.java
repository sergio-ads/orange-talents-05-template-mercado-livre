package br.com.zupacademy.mercadolivre.compartilhado.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mercadolivre.model.Usuario;

@Component
@Profile("dev")
public class EnviaEmailFake implements EnviaEmail {
	
	@Override
	public void enviar(Usuario usuario, String mensagem) {
		System.out.println("E-mail enviado para: " +usuario.getLogin() +", Mensagem: " +mensagem);		
	}
	
}
