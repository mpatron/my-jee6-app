package org.jobjects.orm.tools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractFacade<T> implements Facade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#create(T)
	 */
	@Override
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#save(T)
	 */
	@Override
	public T save(T entity) {
		return getEntityManager().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#remove(T)
	 */
	@Override
	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#find(java.lang.Object)
	 */
	@Override
	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#findAll()
	 */
	@Override
	public List<T> findAll() {
		System.out.println("findAll");
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#findRange(int[])
	 */
	@Override
	public List<T> findRange(int[] range) {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(entityClass);
		cq.select(cq.from(entityClass));
		TypedQuery<T> q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jobjects.orm.tools.Facade#count()
	 */
	@Override
	public long count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(entityClass)));
		return getEntityManager().createQuery(cq).getSingleResult();

	}

	/**
	 * @see be.bzbit.framework.domain.repository.GenericRepository
	 *      #findByNamedQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> findByNamedQuery(final String name, Object... params) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(name,
				entityClass);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}
}