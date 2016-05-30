package com.levi9.taster.core.jpa;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.levi9.taster.commons.model.Identifiable;

/**
 * The abstract JPA object that defines common properties and methods.
 * All JPA entities should extend it.
 *
 * @author m.zorboski
 */
@MappedSuperclass
abstract class AbstractJpaObject implements Identifiable {

	private static final long serialVersionUID = 1L;
	
	private static final long UNDEFINED_ID_LONG_VALUE = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id = UNDEFINED_ID_LONG_VALUE;
	
	/**
	 * Checks if is object is saved based on the value of its ID.
	 *
	 * @return true, if is saved
	 */
	public boolean isSaved() {
		return (this.id != null && this.id != UNDEFINED_ID_LONG_VALUE);
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public String getIdAsString() {
		if (!isSaved()) {
			throw new IllegalStateException("Entity is still not persisted in data store.");
		}
		return String.valueOf(id);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
}
