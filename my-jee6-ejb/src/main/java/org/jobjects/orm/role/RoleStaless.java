package org.jobjects.orm.role;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jobjects.orm.tools.AbstractFacade;
import org.jobjects.orm.tools.AppConstants;

@Stateless
@Local({RoleFacade.class})
public class RoleStaless extends AbstractFacade<Role> implements RoleFacade {

	public RoleStaless() {
		super(Role.class);
	}
	
	@PersistenceContext(unitName = AppConstants.PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
