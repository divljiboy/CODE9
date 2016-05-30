package rs.code9.taster.repository;

import java.util.List;

/**
 * JPA access layer interface.
 * 
 * @author r.zuljevic
 */
public interface BaseRepository<E> {

	/**
	 * Find and return entity with passed id.
	 *
	 * @param id
	 *            of the entity to return
	 * @return entity with passed id or null if not found
	 */
	E findOne(Long id);

	/**
	 * Return back all existing entities.
	 *
	 * @return list of existing entities, empty list if there are no entities
	 */
	List<E> findAll();

	/**
	 * Save entity and return saved instance (with id set).
	 *
	 * @param category
	 *            to be saved
	 * @return saved instance
	 */
	E save(E category);

	/**
	 * Remove entity with passed id.
	 *
	 * @param id
	 *            of the entity to be removed
	 * @throws IllegalArgumentException
	 *             if there is no entity with passed id
	 */
	void delete(Long id) throws IllegalArgumentException;

}
