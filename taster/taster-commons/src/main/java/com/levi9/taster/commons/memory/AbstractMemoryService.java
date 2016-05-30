package com.levi9.taster.commons.memory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.levi9.taster.commons.model.Identifiable;
import com.levi9.taster.commons.model.Page;
import com.levi9.taster.commons.service.CrudService;

/**
 * {@link CrudService} implementation backed by {@link Map}.
 * Indented to be used for in memory implementations of the services.
 * Does not persists state between application restarts.
 * 
 * @author d.gajic
 */
public abstract class AbstractMemoryService<T extends Identifiable> implements CrudService<T> {
	
	protected Map<Long, T> map = new LinkedHashMap<>();
	protected final AtomicLong sequence = new AtomicLong(1);
	
	@Override
	public T save(T t) {
		map.put(t.getId(), t);
		return t;
	}
	
	@Override
	public void delete(Long id) {
		map.remove(id);		
	}
	
	@Override
	public T findOne(Long id) {		
		return map.get(id);
	}
	
	@Override
	public Page<T> findAll(int page, int size) {
		if (page < 0) {
			throw new IllegalArgumentException("Page must be greater or equals to zero");
		}
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than zero");
		}
		final int len = map.size();
		int start = page * size;
		int end = (page + 1) * size;

		if (end > len) {
			end = len;
		}
		if (start > end) {
			start = end;
		}
		return new SimplePage(page, size, len, new LinkedList<T>(map.values()).subList(start, end));
	}	
	
	/**
	 * Simple implementation of the {@link Page}.
	 * 
	 * @author d.gajic
	 *
	 * @param <T>
	 */
	private class SimplePage implements Page<T> {
		private int number;
		private int size;
		private int totalElements;
		private List<T> content;
		
		private SimplePage(int number, int size, int totalElements, List<T> content) {
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
		public List<T> getContent() {
			return content;
		}
	}
}
