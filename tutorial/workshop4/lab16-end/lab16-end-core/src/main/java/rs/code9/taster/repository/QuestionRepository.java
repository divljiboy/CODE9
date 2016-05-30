package rs.code9.taster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.code9.taster.model.Question;

/**
 * Question jpa access layer interface.
 * @author s.bogdanovic
 */
public interface QuestionRepository extends JpaRepository<Question, Long>{

    /**
     * Find and return questions by category id.
     * @param id the category id
     * @return list of questions from a category
     */
    List<Question> findQuestionsByCategoryId(Long id);
}
