package br.com.zupacademy.mercadolivre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/*
 * Filtro básico utilizado para efetuar login a cada requisição da API no profile dev utilizando basic auth.
 * O mesmo efetua o login definido com as credenciais abaixo, que devem ser as mesmas da classe DevSecurityConfigurations.
 */
public class AutenticacaoViaTokenFilterDev extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("teste@teste.com", "password");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filterChain.doFilter(request, response);
	}


}
