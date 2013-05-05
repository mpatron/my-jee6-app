package org.jobjects.orm.message;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.jobjects.orm.discussion.Discussion;
import org.jobjects.orm.discussion.DiscussionFacade;
import org.jobjects.orm.tools.EJBContainerEmbedded;
import org.testng.annotations.Test;

public class MessageStalessTest {

	private Logger log = Logger.getLogger(getClass().getName());

	// private String jndiStaless =
	// "java:global/my-jee-app/classes/MessagesStaless!org.jobjects.orm.message.MessageFacade";
	private String jndiStaless = "java:global/my-jee-app/classes/MessageStaless";
	private String jndiDiscussionStaless = "java:global/my-jee-app/classes/DiscussionStaless";
	private Context ctx;

	public MessageStalessTest() {
		ctx = EJBContainerEmbedded.getInstance().getContext();
	}

	@Test
	public void MessageStaless() {
		log.log(Level.INFO, "#### >> MessageStaless");
	}

	@Test(groups = "MaSuite")
	public void testCreateByDiscussion() throws Exception {
		log.log(Level.INFO, "#### >> testCreateByDiscussion");
		try {
			DiscussionFacade facadeDiscussion = (DiscussionFacade) ctx.lookup(jndiDiscussionStaless);
			assertNotNull(facadeDiscussion);
			Discussion discussion = new Discussion();
			discussion.setLeTitre("mon titre à moi !");
			discussion.setLeTexte("le grand texte.");

			Message msg=new Message();
			msg.setLeTexte("mon message");
			discussion.getMessages().add(msg);
			msg.setDiscussion(discussion);
			facadeDiscussion.create(discussion);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testCreateByMessage() throws Exception {
		log.log(Level.INFO, "#### >> testCreateByMessage");
		try {
			DiscussionFacade facadeDiscussion = (DiscussionFacade) ctx.lookup(jndiDiscussionStaless);
			assertNotNull(facadeDiscussion);
			MessageFacade facade = (MessageFacade) ctx.lookup(jndiStaless);
			assertNotNull(facade);
			
			List<Discussion> discussions =facadeDiscussion.findAll();
			for (Discussion discussion : discussions) {
				Message msg=new Message();
				msg.setLeTexte("mon message");
				msg.setDiscussion(discussion);
				facade.create(msg);
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
