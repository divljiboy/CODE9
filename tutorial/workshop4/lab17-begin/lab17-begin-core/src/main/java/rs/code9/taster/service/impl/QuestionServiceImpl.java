package rs.code9.taster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.code9.taster.model.Answer;
import rs.code9.taster.model.Question;
import rs.code9.taster.repository.QuestionRepository;
import rs.code9.taster.service.QuestionService;

import java.util.List;

/**
 * {@link QuestionService} implementation.
 *
 * @author s.bogdanovic
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * @see rs.code9.taster.service.QuestionService#addAnswer(rs.code9.taster.model.Question)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addAnswer(Question question) {
        Answer answer = new Answer();
        question.getAnswers().add(answer);
    }

    /**
     * @see rs.code9.taster.service.QuestionService#removeAnswer(rs.code9.taster.model.Question, Long)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeAnswer(Question question, Long answerIndex) throws IllegalArgumentException {
        if (answerIndex == null || answerIndex < 0 || answerIndex > question.getAnswers().size() - 1) {
            throw new IllegalArgumentException(String.format("Error: Tried to delete non-existing entity with id=%d.", answerIndex));
        }
        question.getAnswers().remove(answerIndex.intValue());
    }

    /**
     * @see rs.code9.taster.service.QuestionService#findByCategoryId(Long)
     */
    @Override
    public List<Question> findByCategoryId(Long categoryId) {
        return questionRepository.findQuestionsByCategoryId(categoryId);
    }

    /**
     * @see rs.code9.taster.service.QuestionService#getOne(Long)
     */
    @Override
    public Question getOne(Long id) {
        return questionRepository.findOne(id);
    }

    /**
     * @see rs.code9.taster.service.QuestionService#findAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    /**
     * @see rs.code9.taster.service.QuestionService#save(rs.code9.taster.model.AbstractBaseEntity)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    /**
     * @see rs.code9.taster.service.QuestionService#remove(Long)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void remove(Long id) throws IllegalArgumentException {
        questionRepository.delete(id);
    }

}
