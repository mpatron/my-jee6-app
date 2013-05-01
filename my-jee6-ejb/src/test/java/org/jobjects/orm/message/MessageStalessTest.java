package org.jobjects.orm.message;

import static org.testng.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.jobjects.orm.person.Person;
import org.jobjects.orm.person.PersonFacade;
import org.jobjects.orm.tools.EJBContainerEmbedded;
import org.testng.annotations.Test;

public class MessageStalessTest {

	private Logger log = Logger.getLogger(getClass().getName());

	// private String jndiStaless =
	// "java:global/my-jee-app/classes/MessagesStaless!org.jobjects.orm.message.MessageFacade";
	private String jndiStaless = "java:global/my-jee-app/classes/MessageStaless";

	private Context ctx;

	public MessageStalessTest() {
		ctx = EJBContainerEmbedded.getInstance().getContext();
	}

	@Test
	public void MessageStaless() {
		throw new RuntimeException("Test not implemented");
	}

	@Test(groups = "MaSuite")
	public void testCreate() throws Exception {
		Logger.getLogger(getClass().getName()).log(Level.INFO,
				"#### >> testCreate");
		// try {
//		MessageFacade facade = (MessageFacade) ctx.lookup(jndiStaless);
//
//		if (facade.find("mpatron") != null) {
//			Message key = new Message();
//			key.setLogin("mpatron");
//			facade.remove(key);
//		}
//
//		Message person = new Message();
//		person.setLogin("mpatron");
//		person.setEmail("mickael_patron@hotmail.com");
//		person.setFirstName("Mickael");
//		person.setLastName("Patron");
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		person.setBirthDate(sdf.parse("29/02/2012"));
//		facade.create(person);
//
//		assertNotNull(facade.find("mpatron"));
		/*
		 * } catch (Exception e) {
		 * Logger.getLogger(PersonStalessTest.class.getName()).log(
		 * Level.SEVERE, e.getMessage(), e); }
		 */
	}

}
