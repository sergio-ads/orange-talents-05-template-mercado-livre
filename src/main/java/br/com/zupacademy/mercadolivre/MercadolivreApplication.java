package br.com.zupacademy.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class MercadolivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolivreApplication.class, args);
	}

}
