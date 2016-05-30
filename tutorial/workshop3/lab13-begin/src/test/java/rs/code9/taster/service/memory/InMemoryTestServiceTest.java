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
	 * Test method -
	 * {@link TestService#addQuestion(rs.code9.taster.model.Test, Question)}
	 */
	@Test
	public void testAddAnswer() {
		Question q = new Question();
		q.setId(1L);
		rs.code9.taster.model.Test t = service.findOne(1L);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 0);

		t.addQuestion(q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 1);
		Assert.assertTrue(t.getQuestions().iterator().hasNext()
				&& t.getQuestions().iterator().next() != null);
	}

	/**
	 * Test method -
	 * {@link TestService#removeQuestion(rs.code9.taster.model.Test, Question)}
	 */
	@Test
	public void testRemoveAnswer() {
		Question q = new Question();
		q.setId(1L);
		rs.code9.taster.model.Test t = service.findOne(1L);
		t.addQuestion(q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 1);
		Assert.assertTrue(t.getQuestions().iterator().hasNext()
				&& t.getQuestions().iterator().next() != null);

		t.addQuestion(q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 0);
	}
}
