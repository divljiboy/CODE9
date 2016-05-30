package rs.code9.taster.service;

import java.util.List;

import rs.code9.taster.model.Category;
import rs.code9.taster.model.Question;

/**
 * Service for question management.
 * 
 * @author d.gajic
 */
public interface QuestionService extends CrudService<Question> {

	/**
	 * Return all questions within the same category.
	 * 
	 * @param categoryId
	 * @return list of questions associated with provided category
	 */
	List<Question> findByCategory(Category category);

	/**
	 * Adds new answer to the question.
	 * 
	 * @param question
	 *            the question to which new answer is added
	 */
	void addAnswer(Question question);

	/**
	 * Removes the answer with specified id from the question.
	 * 
	 * @param question
	 *            the question from which the answer is removed
	 * @param answerId
	 *            the id of the answer that is removed
	 * @throws IllegalArgumentException
	 *             thrown when no answer with provided id is found
	 */
	void removeAnswer(Question question, Long answerId)
			throws IllegalArgumentException;
}
