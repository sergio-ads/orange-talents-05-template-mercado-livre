package br.com.zupacademy.mercadolivre.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumStringValidator implements ConstraintValidator<EnumString, String> {
	
	private Class<? extends Enum<?>> klass;
	
	@Override
	public void initialize(EnumString params) {
		klass = params.domainClass();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {		
		List<String> enumValues = Stream.of(klass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
		
		context.disableDefaultConstraintViolation();
		if(!enumValues.contains(value)) {
			context.buildConstraintViolationWithTemplate("Valor inválido, deve ser um dos seguintes: " +enumValues).addConstraintViolation();
			return false;
		}
		return true;
	}

}
