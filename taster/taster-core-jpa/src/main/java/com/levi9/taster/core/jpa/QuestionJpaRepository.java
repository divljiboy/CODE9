package com.levi9.taster.core.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.levi9.taster.core.Question;

/**
 * JPA Repository for {@link JpaQuestion}.
 * 
 * @author r.horvat
 */
@Repository
public interface QuestionJpaRepository extends JpaRepository<JpaQuestion, Long>, QueryDslPredicateExecutor<JpaQuestion> {

    /**
     * Returns list of questions with given category ID.
     *
     * @param id of category
     * @return list of questions
     */
    List<Question> findQuestionsByCategoryId(Long id);
    
    /**
     * Returns list of questions with given fixed test template ID.
     * 
     * @param id of fixed test template
     * @return list of questions
     */
    List<Question> findQuestionsByFixedTestTemplatesId(Long id);
    
}
