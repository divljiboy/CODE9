package rs.code9.taster.service.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.code9.taster.model.Answer;
import rs.code9.taster.model.Question;
import rs.code9.taster.service.QuestionService;

/**
 * In memory backed {@link QuestionService} implementation.
 * 
 * @author d.gajic
 */
@Service
public class InMemoryQuestionService extends AbstractInMemoryService<Question> implements QuestionService {

	/**
	 * @see rs.code9.taster.service.QuestionService#findByCategoryId(java.lang.Long)
	 */
	@Override
	public List<Question> findByCategoryId(Long categoryId) {
		List<Question> res = new ArrayList<>();
		for (Question q : findAll()) {
			if (q.getCategory() != null && q.getCategory().getId().equals(categoryId)) {
				res.add(q);
			}
		}
		return res;
	}

	/**
	 * @see rs.code9.taster.service.QuestionService#addAnswer(rs.code9.taster.model.Question)
	 */
	@Override
	public void addAnswer(Question question) {
		Answer answer = new Answer();
		// set id to the (future) index in the list
		answer.setId(new Long(question.getAnswers().size()));
		question.getAnswers().add(answer);
	}

	/**
	 * @see rs.code9.taster.service.QuestionService#removeAnswer(rs.code9.taster.model.Question, java.lang.Long)
	 */
	@Override
	public void removeAnswer(Question question, Long answerId) throws IllegalArgumentException {
		if (answerId == null || answerId < 0 || answerId > question.getAnswers().size() - 1) {
			throw new IllegalArgumentException(String.format("Error: Tried to remove non-existing entity with id=%d.", answerId));
		}
		// id is the index in the list
		question.getAnswers().remove(answerId.intValue());
	}
}
