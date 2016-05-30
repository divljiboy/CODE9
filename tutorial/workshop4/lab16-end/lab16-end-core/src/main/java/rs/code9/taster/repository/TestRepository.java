package rs.code9.taster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.code9.taster.model.Test;

/**
 * Test jpa access layer interface.
 * @author s.bogdanovic
 */
public interface TestRepository extends JpaRepository<Test, Long> {
}
