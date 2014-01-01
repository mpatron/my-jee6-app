package org.jobjects.orm.discussion;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jobjects.orm.tools.AbstractFacade;
import org.jobjects.orm.tools.AppConstants;

@Stateless
@Local({DiscussionFacade.class})
public class DiscussionStaless extends AbstractFacade<Discussion> implements DiscussionFacade {

	public DiscussionStaless() {
		super(Discussion.class);
	}
	
	@PersistenceContext(unitName = AppConstants.PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
 