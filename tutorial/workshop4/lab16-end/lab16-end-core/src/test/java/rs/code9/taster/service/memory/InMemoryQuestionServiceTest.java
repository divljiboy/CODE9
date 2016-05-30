package rs.code9.taster.service.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.code9.taster.model.Category;
import rs.code9.taster.model.Question;
import rs.code9.taster.service.QuestionService;

/**
 * Test class for {@link InMemoryQuestionService}.
 * 
 * @author p.stanic
 */
public class InMemoryQuestionServiceTest {

	/**
	 * The service to test
	 */
	private QuestionService service;

	/**
	 * Sets up one question entity used for testing.
	 */
	@Before
	public void setUp() {
		service = new InMemoryQuestionService();
		Category c1 = new Category();
		c1.setId(1L);
		c1.setName("Java");
		Category c2 = new Category();
		c2.setId(2L);
		c2.setName("Scala");
		Question q1 = new Question();
		q1.setId(1L);
		q1.setCategory(c1);
		q1.setContent("Test content");
		Question q2 = new Question();
		q2.setId(2L);
		q2.setCategory(c1);
		q2.setContent("Test content");
		Question q3 = new Question();
		q3.setId(3L);
		q3.setCategory(c2);
		q3.setContent("Test content");
		Question q4 = new Question();
		q4.setId(4L);
		q4.setCategory(c2);
		q4.setContent("Test content");
		Question q5 = new Question();
		q5.setId(5L);
		q5.setContent("Test content");
		service.save(q1);
		service.save(q2);
		service.save(q3);
		service.save(q4);
		service.save(q5);
	}

	/**
	 * Test method - {@link QuestionService#findByCategoryId(Long)}.
	 */
	@Test
	public void testFindByCategoryId() {
		List<Question> questions = service.findByCategoryId(1L);

		Assert.assertNotNull(questions);
		Assert.assertTrue(questions.size() == 2);

		questions = service.findByCategoryId(2L);

		Assert.assertNotNull(questions);
		Assert.assertTrue(questions.size() == 2);

		questions = service.findByCategoryId(3L);

		Assert.assertNotNull(questions);
		Assert.assertTrue(questions.size() == 0);
	}

	/**
	 * Test method - {@link QuestionService#addAnswer(Question)}.
	 */
	@Test
	public void testAddAnswer() {
		Question q = service.getOne(1L);

		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 0);

		service.addAnswer(q);

		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 1);
		Assert.assertNotNull(q.getAnswers().get(0));
	}

	/**
	 * Test method - {@link QuestionService#removeAnswer(Question, Long)}.
	 */
	@Test
	public void testRemoveAnswer() {
		Question q = service.getOne(1L);
		service.addAnswer(q);

		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 1);
		Assert.assertNotNull(q.getAnswers().get(0));

		service.removeAnswer(q, 0L);

		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 0);
	}

	/**
	 * Test method - {@link QuestionService#removeAnswer(Question, Long)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAnswerIllegalArgument() {
		Question q = service.getOne(1L);
		Assert.assertTrue(q.getAnswers().size() < 1);
		service.removeAnswer(q, 0L);
	}
}
