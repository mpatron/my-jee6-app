package org.jobjects.orm.person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jobjects.orm.tools.AbstractFacade;
import org.jobjects.orm.tools.AppConstants;

@Stateless
@Local({PersonFacade.class})
public class PersonStaless extends AbstractFacade<Person> implements PersonFacade {

	public PersonStaless() {
		super(Person.class);
	}
	
	@PersistenceContext(unitName = AppConstants.PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
 