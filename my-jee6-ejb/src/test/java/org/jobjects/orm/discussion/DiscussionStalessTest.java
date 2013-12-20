package org.jobjects.orm.discussion;

import static org.testng.Assert.assertNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.jobjects.orm.tools.EJBContainerEmbedded;
import org.testng.annotations.Test;

public class DiscussionStalessTest {
	private Logger log = Logger.getLogger(getClass().getName());

	// private String jndiStaless =
	// "java:global/my-jee-app/classes/MessagesStaless!org.jobjects.orm.message.DiscussionFacade";
	//private String jndiStaless = "java:global/my-jee-app/classes/DiscussionStaless";
	private String jndiStaless = "java:global/my-jee6-ejb/DiscussionStaless";

	private Context ctx;

	public DiscussionStalessTest() {
		ctx = EJBContainerEmbedded.getInstance().getContext();
	}

	@Test(groups = "MaSuite")
	public void testCreate() throws Exception {
		log.log(Level.INFO, "#### >> testCreate");
		try {
			DiscussionFacade facade = (DiscussionFacade) ctx
					.lookup(jndiStaless);
			assertNotNull(facade);

			Discussion discussion = new Discussion();
			discussion.setLeTitre("mon titre à moi !");
			discussion.setLeTexte("le grand texte.");
			facade.create(discussion);

			assertNotNull(facade.findAll());

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
