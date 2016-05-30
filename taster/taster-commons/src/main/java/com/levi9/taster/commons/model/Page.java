/**
 * 
 */
package com.levi9.taster.commons.model;

import java.util.List;

/**
 * Used to wrap list of objects into a representation
 * which carry paging info. 
 * 
 * @author d.gajic
 */
public interface Page<T> {
	
	/**
	 * Page number.
	 * 
	 * @return
	 */
	int getNumber();
	
	/**
	 * Size of the page (number of elements per page).
	 * 
	 * @return
	 */
	int getSize();
	
	/**
	 * Total number of elements in the list.
	 * 
	 * @return
	 */
	int getTotalElements();
	
	/**
	 * Content of a page (list of objects limited to size).
	 * 
	 * @return
	 */
	List<? extends T> getContent();
}
