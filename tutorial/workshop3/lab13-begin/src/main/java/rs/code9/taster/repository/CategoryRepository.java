package rs.code9.taster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.code9.taster.model.Category;

/**
 * Category JPA access layer interface.
 * 
 * @author r.zuljevic
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
