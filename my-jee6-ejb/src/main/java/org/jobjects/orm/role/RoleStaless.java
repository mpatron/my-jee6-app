package org.jobjects.orm.role;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jobjects.orm.tools.AbstractFacade;

@Stateless
@Local({RoleFacade.class})
public class RoleStaless extends AbstractFacade<Role> implements RoleFacade {

	public RoleStaless() {
		super(Role.class);
	}
	
	@PersistenceContext(unitName = "aa-jpa")
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
