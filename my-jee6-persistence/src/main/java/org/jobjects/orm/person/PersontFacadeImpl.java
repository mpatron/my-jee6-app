package org.jobjects.orm.person;

import org.jobjects.orm.tools.AbstractFacade;

public class PersontFacadeImpl extends AbstractFacade<Person> implements
		PersonFacade {

	public PersontFacadeImpl() {
		super(Person.class);
	}

}
