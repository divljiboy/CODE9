/**
 * 
 */
package rs.code9.taster.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import rs.code9.taster.model.Category;

/**
 * In memory backed {@link CategoryService} implementation.
 * 
 * @author d.gajic
 */
public class InMemoryCategoryService implements CategoryService {
	
	private Map<Long, Category> map = new HashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);


	/* (non-Javadoc)
	 * @see rs.code9.taster.service.CategoryService#findOne(java.lang.Long)
	 */
	public Category findOne(Long id) {
		return map.get(id);
	}

	/* (non-Javadoc)
	 * @see rs.code9.taster.service.CategoryService#findAll()
	 */
	public List<Category> findAll() {		
		return new ArrayList<>(map.values());
	}

	/* (non-Javadoc)
	 * @see rs.code9.taster.service.CategoryService#save(rs.code9.taster.model.Category)
	 */
	public Category save(Category category) {
		if (category.getId() == null) {
			category.setId(sequence.getAndIncrement());
		}
		map.put(category.getId(), category);
		return category;
	}

	/* (non-Javadoc)
	 * @see rs.code9.taster.service.CategoryService#remove(java.lang.Long)
	 */
	public void remove(Long id) throws IllegalArgumentException {		
		Category removed = map.remove(id);
		if (removed == null) {
			throw new IllegalArgumentException("Removing unexisting category with id=" + id);
		}
	}

}
