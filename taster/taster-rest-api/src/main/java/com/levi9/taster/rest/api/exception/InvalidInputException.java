package com.levi9.taster.rest.api.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

/**
 * Wraps {@link FieldError} messages into a {@link Map}<field, errorMessage> representation.
 * 
 * @author r.horvat
 */
public class InvalidInputException extends RuntimeException {

	private Map<String, String> messages;
	
	private static final long serialVersionUID = -7187504147077702538L;

	public InvalidInputException(List<FieldError> errors) {
		messages = new HashMap<String, String>();
		for (FieldError fieldError : errors) {
			messages.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}
	
	public Map<String, String> getMessages() {
		return messages;
	}
}
