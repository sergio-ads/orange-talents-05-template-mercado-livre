package br.com.zupacademy.mercadolivre.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {NotExistsValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NaoExiste {

	String message() default "O atributo já existe no banco de dados";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?> domainClass();

	String fieldName();
	
}
