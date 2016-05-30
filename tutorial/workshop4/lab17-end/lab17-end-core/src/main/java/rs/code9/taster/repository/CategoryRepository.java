package rs.code9.taster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.code9.taster.model.Category;

/**
 * Category jpa access layer interface.
 * @author s.bogdanovic
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
