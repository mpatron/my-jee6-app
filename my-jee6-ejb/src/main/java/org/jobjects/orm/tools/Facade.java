package org.jobjects.orm.tools;

import java.util.List;

public interface Facade<T> {

	public abstract void create(T entity);

	public abstract void save(T entity);

	public abstract void remove(T entity);

	public abstract T find(Object id);

	public abstract List<T> findAll();

	public abstract List<T> findRange(int[] range);

	public abstract long count();
	
	public abstract List<T> findByNamedQuery(final String name, Object... params);

}