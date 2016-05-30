package rs.code9.taster.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.code9.taster.model.Category;

public class InMemoryCategoryServiceTest {
	
	private CategoryService categoryService;
	
	@Before
	public void setUp() {
		
		categoryService = new InMemoryCategoryService();
		
		Category categoryJava = new Category();
		categoryJava.setId(1L);
		categoryJava.setName("Java");
		Category categoryC = new Category();
		categoryC.setId(2L);
		categoryC.setName("C#");
		
		categoryService.save(categoryJava);
		categoryService.save(categoryC);
	}

	@Test
	public void testFindOne() {
		Category cat1 = categoryService.findOne(1L);
		Assert.assertNotNull(cat1);
		Assert.assertEquals("Java", cat1.getName());
	}

	@Test
	public void testFindAll() {
		List<Category> cats = categoryService.findAll();
		Assert.assertEquals(2, cats.size());
		Category cat1 = cats.get(0);
		Category cat2 = cats.get(1);
		if (cat1.getId().equals(1L)) {
			Assert.assertEquals("Java", cat1.getName());
			Assert.assertTrue(cat2.getId().equals(2L) && cat2.getName().equals("C#"));
		} else {
			Assert.assertTrue(cat1.getId().equals(2L) && cat1.getName().equals("C#"));
			Assert.assertTrue(cat2.getId().equals(1L) && cat2.getName().equals("Java"));
		}
	}

	@Test
	public void testSave() {
		Category cat = new Category();
		cat.setName("New category");
		Category saved = categoryService.save(cat);
		
		Assert.assertNotNull(saved.getId());		
		Assert.assertEquals("New category", categoryService.findOne(saved.getId()).getName());
	}

	@Test
	public void testRemove() {
		Assert.assertNotNull(categoryService.findOne(1L));
		Assert.assertNotNull(categoryService.findOne(2L));
		
		categoryService.remove(1L);
		
		Assert.assertNull(categoryService.findOne(1L));
		Assert.assertNotNull(categoryService.findOne(2L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveIllegalArgument() {
		Assert.assertNull(categoryService.findOne(3L));		
		categoryService.remove(3L);
	}
}
