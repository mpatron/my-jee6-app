package org.jobjects.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "UsersStateless")
@Local({ Users.class })
public class UsersImpl implements Users {

	public UsersImpl() {
	}

	@PersistenceContext(unitName = "aa-jpa")
	protected EntityManager entityManager;

	public void create(User bean) throws Exception {
		entityManager.persist(bean);
		entityManager.flush();
	}

	public User save(User bean) throws Exception {
		return entityManager.merge(bean);
	}

	public void remove(User bean) throws Exception {
		entityManager.remove(bean);
	}

	public User load(String beanPk) throws Exception {
		User returnValue = null;
		User user = entityManager.find(User.class, beanPk);
		if (user != null) {
			entityManager.refresh(user);
			returnValue = user;
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public List<User> findUserByLogin(String login) {
		Query query = entityManager.createNamedQuery("findUserByLogin");
		query.setParameter("login", login);
		return (List<User>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUserByFirstName(String firstName) {
		try {
			/**
			 * voir s'il faut fabriquer un ejb-jar.xml avec les réfénrece
			 * JNDI. cf : Example SessionBean ejb-jar.xml file with persistence
			 * context http://en.wikibooks.org/wiki/Java_Persistence/Runtime
			 */
			Query query = entityManager
					.createNamedQuery("findAllUserByFirstName");
			query.setParameter("firstName", firstName);
			return (List<User>) query.getResultList();
		} catch (Throwable e) {
			e.printStackTrace();
			Logger.getLogger(UsersImpl.class.getName()).log(Level.SEVERE, null,
					e);
		}
		return new ArrayList<User>();
	}
}
