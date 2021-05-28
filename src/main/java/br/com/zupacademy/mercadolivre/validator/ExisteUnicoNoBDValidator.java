package br.com.zupacademy.mercadolivre.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteUnicoNoBDValidator implements ConstraintValidator<ExisteUnico, String> {
	
	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public void initialize(ExisteUnico params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " +klass.getName() +" a where a." +domainAttribute +"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		
		context.disableDefaultConstraintViolation();
		if(list.size() < 1) {
			context.buildConstraintViolationWithTemplate("O atributo não existe no banco de dados").addConstraintViolation();
			return false;
		} else if(list.size() > 1) {
			context.buildConstraintViolationWithTemplate("O atributo não é único no banco de dados").addConstraintViolation();
			return false;
		}
		return true;
	}
	

}
