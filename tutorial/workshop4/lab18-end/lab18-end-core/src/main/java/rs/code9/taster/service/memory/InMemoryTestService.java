package rs.code9.taster.service.memory;

import org.springframework.stereotype.Service;

import rs.code9.taster.model.Question;
import rs.code9.taster.model.Test;
import rs.code9.taster.service.TestService;

/**
 * In memory backed {@link TestService} implementation.
 * 
 * @author p.stanic
 */
@Service
public class InMemoryTestService extends AbstractInMemoryService<Test> implements TestService {

	/**
	 * @see rs.code9.taster.service.TestService#addQuestion(rs.code9.taster.model.Test, rs.code9.taster.model.Question)
	 */
	@Override
	public void addQuestion(Test test, Question question) throws IllegalArgumentException {
		if (question == null || test.getQuestions().contains(question)) {
			throw new IllegalArgumentException("Error: No question provided or question is already in test.");
		}
		test.getQuestions().add(question);
	}

	/**
	 * @see rs.code9.taster.service.TestService#removeQuestion(rs.code9.taster.model.Test,
	 *      rs.code9.taster.model.Question)
	 */
	@Override
	public void removeQuestion(Test test, Question question) throws IllegalArgumentException {
		if (question == null || !test.getQuestions().contains(question)) {
			throw new IllegalArgumentException("Error: No question provided or question is not in test.");
		}
		test.getQuestions().remove(question);
	}
}
