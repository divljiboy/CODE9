package com.levi9.taster.core.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.levi9.taster.core.Answer;

/**
 * JPA Repository for {@link JpaAnswer}.
 * 
 * @author r.horvat
 */
@Repository
public interface AnswerJpaRepository extends JpaRepository<JpaAnswer, Long>, QueryDslPredicateExecutor<JpaAnswer> {

    /**
     * Returns list of answers for given question ID.
     *
     * @param id of question
     * @return list of answers
     */
    List<Answer> findAnswersByQuestionId(Long id);
    
}
