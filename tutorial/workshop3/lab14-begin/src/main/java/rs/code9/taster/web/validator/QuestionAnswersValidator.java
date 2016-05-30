package rs.code9.taster.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.code9.taster.model.Answer;
import rs.code9.taster.model.Question;

/**
 * Validates that there is minimum required number of answers and that at least
 * one is correct.
 * 
 * @author p.stanic
 */
@Component
public class QuestionAnswersValidator implements Validator {

	private static final int MINIMUM_NR_OF_ANSWERS = 2;
	private static final int MINIMUM_NR_OF_CORRECT_ANSWERS = 1;
	private static final String ANSWERS_FIELD_NAME = "answers";
	private static final String MINIMUM_TWO_ANSWERS_MESSAGE_KEY = "question.answers.minimumTwoAnswers";
	private static final String MINIMUM_ONE_CORRECT_ANSWER_MESSAGE_KEY = "question.answers.minimumOneCorrect";

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Question.class.isAssignableFrom(clazz);
	}

	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		if (target != null && supports(target.getClass())) {
			Question question = (Question) target;
			if (question.getAnswers().size() < MINIMUM_NR_OF_ANSWERS) {
				errors.rejectValue(ANSWERS_FIELD_NAME,
						MINIMUM_TWO_ANSWERS_MESSAGE_KEY);
			} else {
				int correctCount = 0;
				for (Answer answer : question.getAnswers()) {
					if (answer.isCorrect()) {
						correctCount++;
						break;
					}
				}
				if (correctCount < MINIMUM_NR_OF_CORRECT_ANSWERS) {
					errors.rejectValue(ANSWERS_FIELD_NAME,
							MINIMUM_ONE_CORRECT_ANSWER_MESSAGE_KEY);
				}
			}
		}
	}
}