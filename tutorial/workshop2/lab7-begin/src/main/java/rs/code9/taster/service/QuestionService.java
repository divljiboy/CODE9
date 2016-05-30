package rs.code9.taster.service;

import java.util.List;

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
	List<Question> findByCategoryId(Long categoryId);
}