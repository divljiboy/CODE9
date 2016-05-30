package rs.code9.taster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.code9.taster.model.Category;
import rs.code9.taster.model.Question;

/**
 * Question JPA access layer interface.
 * 
 * @author s.bogdanovic
 * @author r.zuljevic
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	/**
	 * Find and return questions by category.
	 * 
	 * @param category
	 *            the category
	 * @return list of questions from a category
	 */
	List<Question> findByCategory(Category category);

}
