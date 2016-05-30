package rs.code9.taster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import rs.code9.taster.model.Question;
import rs.code9.taster.model.Test;
import rs.code9.taster.repository.TestRepository;
import rs.code9.taster.service.TestService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Default implementation of {@link TestService}.
 * 
 * @author d.gajic
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    /**
     * @see rs.code9.taster.service.TestService#addQuestion(rs.code9.taster.model.Test, rs.code9.taster.model.Question)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addQuestion(Test test, Question question) throws IllegalArgumentException {
        if (question == null || test.getQuestions().contains(question)) {
            throw new IllegalArgumentException("Error: No question provided or question is already in test.");
        }
        test.getQuestions().add(question);
    }

    /**
     * @see rs.code9.taster.service.TestService#removeQuestion(rs.code9.taster.model.Test, rs.code9.taster.model.Question)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeQuestion(Test test, Question question) throws IllegalArgumentException {
        if (question == null || !test.getQuestions().contains(question)) {
            throw new IllegalArgumentException("Error: No question provided or question is not in test.");
        }
        test.getQuestions().remove(question);
    }

    /**
     * @see rs.code9.taster.service.TestService#getOne(Long)
     */
    @Override
    public Test getOne(Long id) {
        return testRepository.findOne(id);
    }

    /**
     * @see rs.code9.taster.service.TestService#findAll()
     * @return
     */
    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    /**
     * @see rs.code9.taster.service.TestService#save(rs.code9.taster.model.AbstractBaseEntity)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Test save(Test test) {
        return testRepository.save(test);
    }

    /**
     * @see rs.code9.taster.service.TestService#remove(Long)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void remove(Long id) throws IllegalArgumentException {
        testRepository.delete(id);
    }
}
