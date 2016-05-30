package com.levi9.taster.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for {@link JpaCategory}.
 *
 * @author m.zorboski
 */
@Repository
public interface CategoryJpaRepository extends JpaRepository<JpaCategory, Long>, QueryDslPredicateExecutor<JpaCategory> {

}
