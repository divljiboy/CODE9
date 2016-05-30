package com.levi9.taster.commons.service;

import com.levi9.taster.commons.model.Page;

/**
 * Based CRUD service used to align naming of the service methods.
 * 
 * @author d.gajic
 *
 * @param <T> type to persist
 */
public interface CrudService<T> {
	
	/**
	 * Save object to persistent store.
	 * 
	 * @param job
	 * @return
	 */
	T save(T t);
	
	/**
	 * Delete object from persistent store
	 * 
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * Find all objects
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	Page<? extends T> findAll(int page, int size);
	
	/**
	 * Find object by id
	 * 
	 * @param id 
	 * @return the object with the given id or {@literal null} if none has been found
	 */
	T findOne(Long id);
}
