package com.levi9.taster.core.jpa;

import java.util.List;

import com.levi9.taster.commons.model.Page;

/**
 * JPA implementation of {@link Page}.
 *
 * @author m.zorboski
 * @param <T> the generic type
 */
class JpaPage<T> implements Page<T> {
	
	private int number;
	private int size;
	private int totalElements;
	private List<? extends T> content;
	
	JpaPage(int number, int size, int totalElements, List<? extends T> content) {
		this.number = number;
		this.size = size;
		this.totalElements = totalElements;
		this.content = content;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getTotalElements() {
		return totalElements;
	}

	@Override
	public List<? extends T> getContent() {
		return content;
	}
}