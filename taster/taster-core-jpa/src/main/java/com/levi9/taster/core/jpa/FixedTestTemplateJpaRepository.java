package com.levi9.taster.core.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.levi9.taster.core.FixedTestTemplate;
import com.levi9.taster.core.Question;

/**
 * JPA repository for {@link FixedTestTemplate}
 * 
 * @author r.horvat
 */
@Repository
public interface FixedTestTemplateJpaRepository extends JpaRepository<JpaFixedTestTemplate, Long>, QueryDslPredicateExecutor<JpaFixedTestTemplate> {

    /**
     * Returns list of questions with given template id.
     *
     * @param id of fixed test template
     * @return list of questions
     */
	@Query("select t.questions from JpaFixedTestTemplate t where t.id = ? ")
    List<Question> findQuestionsById(Long id);
	
    /**
     * Find by questions id.
     *
     * @param questionId the question id
     * @return the list
     */
    List<JpaFixedTestTemplate> findByQuestionsId(Long questionId);
    
}
