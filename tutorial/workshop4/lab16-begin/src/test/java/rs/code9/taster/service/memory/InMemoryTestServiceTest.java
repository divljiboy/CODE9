package rs.code9.taster.service.memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.code9.taster.model.Question;
import rs.code9.taster.service.TestService;

/**
 * Test class for {@link InMemoryTestService}.
 * 
 * @author p.stanic
 */
public class InMemoryTestServiceTest {

	/**
	 * The service to test
	 */
	private TestService service;

	/**
	 * Sets up one question entity used for testing.
	 */
	@Before
	public void setUp() {
		service = new InMemoryTestService();
		rs.code9.taster.model.Test t = new rs.code9.taster.model.Test();
		t.setId(1L);
		service.save(t);
	}

	/**
	 * Test method - {@link TestService#addQuestion(rs.code9.taster.model.Test, Question)}
	 */
	@Test
	public void testAddAnswer() {
		Question q = new Question();
		q.setId(1L);
		rs.code9.taster.model.Test t = service.getOne(1L);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 0);

		service.addQuestion(t, q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 1);
		Assert.assertNotNull(t.getQuestions().get(0));
	}

	/**
	 * Test method - {@link TestService#removeQuestion(rs.code9.taster.model.Test, Question)}
	 */
	@Test
	public void testRemoveAnswer() {
		Question q = new Question();
		q.setId(1L);
		rs.code9.taster.model.Test t = service.getOne(1L);
		service.addQuestion(t, q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 1);
		Assert.assertNotNull(t.getQuestions().get(0));

		service.removeQuestion(t, q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 0);
	}

	/**
	 * Test method - {@link TestService#removeQuestion(rs.code9.taster.model.Test, Question)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAnswerIllegalArgument() {
		Question q = new Question();
		q.setId(1L);
		rs.code9.taster.model.Test t = service.getOne(1L);
		Assert.assertTrue(t.getQuestions().size() < 1);
		service.removeQuestion(t, q);
	}
}
