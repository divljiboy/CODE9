package com.levi9.taster.core;

import com.levi9.taster.commons.model.Identifiable;

/**
 * Represents an abstract test template with a name.
 * 
 * @author r.horvat
 */
public interface TestTemplate extends Identifiable {

	/**
	 * Name of test template.
	 * 
	 * @return name (mandatory)
	 */
	String getName();
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	String getType();
	
}
