package com.levi9.taster.core;

import com.levi9.taster.commons.model.Identifiable;

/**
 * Represents question category.
 * 
 * @author d.gajic
 */
public interface Category extends Identifiable {
	
	/**
	 * The name of the category (unique).
	 * 
	 * @return
	 */
	String getName();
}
