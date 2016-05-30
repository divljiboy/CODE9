

package rs.code9.taster.service.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
}

