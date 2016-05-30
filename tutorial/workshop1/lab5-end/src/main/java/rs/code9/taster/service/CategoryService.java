package rs.code9.taster.service;

import java.util.List;

import rs.code9.taster.model.Category;

/**
 * Service for category management.
 * 
 * @author d.gajic
 *
 */
public interface CategoryService {
	
	/**
	 * Find and return category with passed id. 
	 * 
	 * @param id of the {@link Category} to return
	 * @return {@link Category} with passed id or null if not found
	 */
	Category findOne(Long id);
	
	/**
	 * Return back all existing categories.
	 * 
	 * @return list of existing categories, empty list if there are no categories
	 */
	List<Category> findAll();
	
	/**
	 * Saved {@link Category} and return saved instance (with id set).
	 * 
	 * @param category to be saved
	 * @return saved instance
	 */
	Category save(Category category);
	
	/**
	 * Remove category with passed id.
	 * 
	 * @param id of the category to be removed
	 * @throws IllegalArgumentException if there is no {@link Category} with passed id
	 */
	void remove(Long id) throws IllegalArgumentException;
}
