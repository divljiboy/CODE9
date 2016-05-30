/**
 * 
 */
package com.levi9.taster.commons.model;

import java.io.Serializable;

/**
 * This interface is to be implemented by the objects which
 * have identity. That usually includes related methods 
 * ({@link #equals(Object)} and {@link #hashCode()}).
 * 
 * @author d.gajic
 *
 */
public interface Identifiable extends Serializable {
	/**
	 * Unique identifier, can be null which usually means new object.
	 * 
	 * @return
	 */
	Long getId();
	
	/**
	 * @see Object#equals(Object)
	 * 
	 * @param obj
	 * @return
	 */
	boolean equals(Object obj);
	
	/**
	 * @see Object#hashCode()
	 * 
	 * @return
	 */
	int hashCode();
}
