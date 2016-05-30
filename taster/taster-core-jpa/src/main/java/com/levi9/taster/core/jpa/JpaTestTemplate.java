package com.levi9.taster.core.jpa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.levi9.taster.core.TestTemplate;

/**
 * JPA implementation of {@link TestTemplate}.
 * 
 * @author r.horvat
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TEMPLATE_TYPE", length = 1)
@Table(name = "test_template")
public abstract class JpaTestTemplate extends AbstractJpaObject implements TestTemplate {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 615242921842073553L;
	
	/**
	 * Name of test template
	 */
    @Column(nullable = false, length = 255)
	protected String name;
    
    @Override
	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
}
