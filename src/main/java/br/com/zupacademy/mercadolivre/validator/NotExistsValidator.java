package br.com.zupacademy.mercadolivre.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistsValidator implements ConstraintValidator<NaoExiste, String> {
	
	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public void initialize(NaoExiste params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " +klass.getName() +" a where a." +domainAttribute +"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		
		if(list.size() == 0) 
			return true;
		else
			return false;
	}
	

}
