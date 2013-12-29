package org.jobjects.orm.message;

import org.jobjects.orm.tools.AbstractFacade;

public class MessageFacadeImpl extends AbstractFacade<Message> implements
		MessageFacade {

	public MessageFacadeImpl() {
		super(Message.class);
	}

}
