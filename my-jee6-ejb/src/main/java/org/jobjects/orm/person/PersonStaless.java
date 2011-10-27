package org.jobjects.orm.person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jobjects.orm.tools.AbstractFacade;

@Stateless(name = "PersonsStaless")
@Local({PersonFacade.class})
public class PersonStaless extends AbstractFacade<Person> implements PersonFacade {

	public PersonStaless() {
		super(Person.class);
	}
	
	@PersistenceContext(unitName = "aa-jpa")
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
