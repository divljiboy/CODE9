package rs.code9.taster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.code9.taster.model.Question;
import rs.code9.taster.model.Test;

/**
 * Category JPA access layer interface.
 * 
 * @author r.zuljevic
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	/**
	 * Find and return tests containing the question.
	 * 
	 * @param question
	 *            the question
	 * @return list of tests containing the question
	 */
	@Query(value = "SELECT t FROM Test t WHERE :q MEMBER OF t.questions")
	public List<Test> findByContainsQuestion(@Param("q") Question question);
	
}
