package org.jobjects.orm.message;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jobjects.orm.tools.AbstractFacade;
import org.jobjects.orm.tools.AppConstants;

@Stateless
@Local({MessageFacade.class})
public class MessageStaless extends AbstractFacade<Message> implements MessageFacade {

	public MessageStaless() {
		super(Message.class);
	}
	
	@PersistenceContext(unitName = AppConstants.PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
 