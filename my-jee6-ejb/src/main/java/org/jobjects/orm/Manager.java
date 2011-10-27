package org.jobjects.orm;


public interface Manager<T, P> {
	void create(final T bean) throws Exception;
	T save(final T bean) throws Exception;
	void remove(final T bean) throws Exception;
	T load(final P beanPk) throws Exception;
}
