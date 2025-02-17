package br.com.zupacademy.mercadolivre.validacao;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsDto {

	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorDto> errors = new ArrayList<>();
	
	public void addGlobalError(String message) {
		globalErrorMessages.add(message);
	}
	
	public void addFieldError(String field, String message) {
		FieldErrorDto fieldError = new FieldErrorDto(field, message);
		errors.add(fieldError);
	}

	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}

	public List<FieldErrorDto> getErrors() {
		return errors;
	}
	

}
