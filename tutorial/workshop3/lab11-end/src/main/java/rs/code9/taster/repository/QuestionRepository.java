package rs.code9.taster.repository;

import java.util.List;

import rs.code9.taster.model.Question;

/**
 * Question JPA access layer interface.
 * 
 * @author s.bogdanovic
 * @author r.zuljevic
 */
public interface QuestionRepository extends BaseRepository<Question> {
	/**
	 * Find and return questions by category id.
	 * 
	 * @param id
	 *            the category id
	 * @return list of questions from a category
	 */
	List<Question> findQuestionsByCategoryId(Long id);

	/**
	 * Find and return questions by test id.
	 * 
	 * @param id
	 *            the test id
	 * @return list of questions from the test
	 */
	List<Question> findQuestionsByTestId(Long id);

}
