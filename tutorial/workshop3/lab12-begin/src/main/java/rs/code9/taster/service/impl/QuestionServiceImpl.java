package rs.code9.taster.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.code9.taster.model.Answer;
import rs.code9.taster.model.Question;
import rs.code9.taster.repository.QuestionRepository;
import rs.code9.taster.service.QuestionService;

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
	public void addAnswer(Question question) {
		Answer answer = new Answer();
		question.getAnswers().add(answer);
	}

	/**
	 * @see rs.code9.taster.service.QuestionService#removeAnswer(rs.code9.taster.model.Question,
	 *      Long)
	 */
	@Override
	public void removeAnswer(Question question, Long answerIndex)
			throws IllegalArgumentException {
		System.out.println(answerIndex);
		if (answerIndex == null || answerIndex < 0
				|| answerIndex > question.getAnswers().size() - 1) {
			throw new IllegalArgumentException(String.format(
					"Error: Tried to delete non-existing entity with id=%d.",
					answerIndex));
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
	 * @see rs.code9.taster.service.QuestionService#findOne(Long)
	 */
	@Override
	public Question findOne(Long id) {
		return questionRepository.findOne(id);
	}

	/**
	 * @see rs.code9.taster.service.QuestionService#findAll()
	 */
	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	/**
	 * @see rs.code9.taster.service.QuestionService#save(rs.code9.taster.model.AbstractBaseEntity)
	 */
	@Override
	@Transactional
	public Question save(Question question) {
		return questionRepository.save(question);
	}

	/**
	 * @see rs.code9.taster.service.QuestionService#remove(Long)
	 */
	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Question question = questionRepository.findOne(id);
		if (question == null) {
			throw new IllegalArgumentException(String.format(
							"Question with id=%d does not exist.",
							id));
		}
		questionRepository.delete(id);
	}

}
