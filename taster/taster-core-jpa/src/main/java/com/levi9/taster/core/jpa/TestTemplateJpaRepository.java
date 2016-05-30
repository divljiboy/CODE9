package com.levi9.taster.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.levi9.taster.core.TestTemplate;

/**
 * JPA repository for {@link TestTemplate}
 * 
 * @author r.horvat
 */
@Repository
public interface TestTemplateJpaRepository extends JpaRepository<JpaTestTemplate, Long>, QueryDslPredicateExecutor<JpaTestTemplate> {

}
