package com.levi9.taster.rest.api.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.levi9.taster.rest.api.resource.AnswerResource;
import com.levi9.taster.rest.api.resource.QuestionResource;

/**
 * Validates question fields against annotations and the number of answers by custom criteria.
 * 
 * @author r.horvat
 */
@Component
public class QuestionAnswersValidator implements org.springframework.validation.Validator, InitializingBean {

	private static final int MINIMUM_NR_OF_ANSWERS = 4;
	private static final int MINIMUM_NR_OF_CORRECT_ANSWERS = 1;
	private static final int MINIMUM_DIFFERENCE = MINIMUM_NR_OF_ANSWERS - MINIMUM_NR_OF_CORRECT_ANSWERS;
	private static final String ANSWERS_FIELD_NAME = "answers";
	private static final String MINIMUM_TOTAL_ANSWERS_MESSAGE_KEY = "Number of total answers must be at least " + MINIMUM_NR_OF_ANSWERS + ".";
	private static final String MINIMUM_CORRECT_ANSWERS_MESSAGE_KEY = "Number of correct answers must be at least " + MINIMUM_NR_OF_CORRECT_ANSWERS + ".";
	private static final String MINIMUM_DIFFERENCE_MESSAGE_KEY = "Number of incorrect answers must be at least " + MINIMUM_DIFFERENCE + " higher than number of correct answers.";
	private static final HttpStatus ERROR_CODE = HttpStatus.NOT_ACCEPTABLE;
	
	private Validator validator;
	
	/**
	 * Initialize Validator bean to catch annotation-based errors.
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
    public void afterPropertiesSet() throws Exception {		
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

	@Override
	public boolean supports(Class<?> clazz) {
		return QuestionResource.class.isAssignableFrom(clazz);
	}

	/**
	 * Get field errors by validating annotations first, then set answer number errors.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		if (target != null && supports(target.getClass())) {
						
			Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
				String propertyPath = constraintViolation.getPropertyPath().toString();
				String message = constraintViolation.getMessage();
				errors.rejectValue(propertyPath, Integer.toString(ERROR_CODE.value()), message);
			}

			QuestionResource question = (QuestionResource) target;
			int totalAnswersCount = question.getAnswers().size();
			if (totalAnswersCount < MINIMUM_NR_OF_ANSWERS) {
				errors.rejectValue(ANSWERS_FIELD_NAME, Integer.toString(ERROR_CODE.value()), MINIMUM_TOTAL_ANSWERS_MESSAGE_KEY);
			} else {
				int correctCount = 0;
				for (AnswerResource answer : question.getAnswers()) {
					if (answer.getCorrect()) {
						correctCount++;
					}
				}
				if (correctCount < MINIMUM_NR_OF_CORRECT_ANSWERS) {
					errors.rejectValue(ANSWERS_FIELD_NAME, Integer.toString(ERROR_CODE.value()), MINIMUM_CORRECT_ANSWERS_MESSAGE_KEY);
				} else if (MINIMUM_DIFFERENCE + correctCount > totalAnswersCount) {
					errors.rejectValue(ANSWERS_FIELD_NAME, Integer.toString(ERROR_CODE.value()), MINIMUM_DIFFERENCE_MESSAGE_KEY);
				}
			}
		}
	}
}
