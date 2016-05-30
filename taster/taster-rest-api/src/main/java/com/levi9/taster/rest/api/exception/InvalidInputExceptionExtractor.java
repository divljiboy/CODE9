package com.levi9.taster.rest.api.exception;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Extracts {@link InvalidInputException} from {@link BindingResult} errors and throws them.
 * 
 * @author r.horvat
 *
 */
public class InvalidInputExceptionExtractor {

	public static void extractAndThrow(BindingResult bindingResult) throws InvalidInputException {
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (errors != null && !errors.isEmpty()) 
			throw new InvalidInputException(errors);
	}
}
