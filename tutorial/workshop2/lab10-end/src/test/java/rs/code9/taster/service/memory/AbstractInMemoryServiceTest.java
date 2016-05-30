package rs.code9.taster.service.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.code9.taster.model.AbstractBaseEntity;
import rs.code9.taster.service.CrudService;

/**
 * Test class for {@link AbstractInMemoryService}.
 * 
 * @author d.gajic
 */
public class AbstractInMemoryServiceTest {

	/**
	 * Inner class which extends {@link AbstractBaseEntity} used as type for
	 * {@link CrudService}.
	 * 
	 * @author d.gajic
	 */
	private static class NamedEntity extends AbstractBaseEntity {
		private static final long serialVersionUID = 6138499804130969529L;
		private String name;
	}

	/**
	 * We can't test abstract class so we use simple implementation of it.
	 * 
	 * @author d.gajic
	 */
	private static class NamedService extends
			AbstractInMemoryService<NamedEntity> implements
			CrudService<NamedEntity> {
	}

	/**
	 * The service to test
	 */
	private CrudService<NamedEntity> service;

	/**
	 * Sets up two entities used for testing.
	 */
	@Before
	public void setUp() {

		service = new NamedService();

		NamedEntity e1 = new NamedEntity();
		e1.setId(1L);
		e1.name = "Java";

		NamedEntity e2 = new NamedEntity();
		e2.setId(2L);
		e2.name = "Scala";

		service.save(e1);
		service.save(e2);
	}

	/**
	 * Test method - {@link AbstractInMemoryService#findOne(Long)}.
	 */
	@Test
	public void testFindOne() {
		NamedEntity e1 = service.findOne(1L);
		Assert.assertNotNull(e1);
		Assert.assertEquals("Java", e1.name);
	}

	/**
	 * Test method - {@link AbstractInMemoryService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<NamedEntity> es = service.findAll();
		Assert.assertEquals(2, es.size());
		NamedEntity e1 = es.get(0);
		NamedEntity e2 = es.get(1);
		if (e1.getId().equals(1L)) {
			Assert.assertEquals("Java", e1.name);
			Assert.assertTrue(e2.getId().equals(2L) && e2.name.equals("Scala"));
		} else {
			Assert.assertTrue(e1.getId().equals(2L) && e1.name.equals("Scala"));
			Assert.assertTrue(e2.getId().equals(1L) && e2.name.equals("Java"));
		}
	}

	/**
	 * Test method - {@link AbstractInMemoryService#save(AbstractBaseEntity)}.
	 */
	@Test
	public void testSave() {
		NamedEntity e = new NamedEntity();
		e.name = "New NamedEntity";
		NamedEntity saved = service.save(e);

		Assert.assertNotNull(saved.getId());
		Assert.assertEquals("New NamedEntity",
				service.findOne(saved.getId()).name);
	}

	/**
	 * Test method - {@link AbstractInMemoryService#remove(Long)}.
	 */
	@Test
	public void testRemove() {
		Assert.assertNotNull(service.findOne(1L));
		Assert.assertNotNull(service.findOne(2L));

		service.remove(1L);

		Assert.assertNull(service.findOne(1L));
		Assert.assertNotNull(service.findOne(2L));
	}

	/**
	 * Test method - {@link AbstractInMemoryService#remove(Long)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveIllegalArgument() {
		Assert.assertNull(service.findOne(3L));
		service.remove(3L);
	}
}