package org.jobjects.orm.message;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jobjects.orm.discussion.Discussion;
import org.jobjects.orm.discussion.DiscussionFacadeImpl;
import org.jobjects.tools.entitymanager.EntityManagerFactorySingleton;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MessageFacadeTest {
  
	private static Logger log = Logger.getLogger(MessageFacadeTest.class
			.getName());

	private static MessageFacadeImpl intanceToDo = null;

	@BeforeClass(groups = "MaSuite")
	public static void setUpBeforeClass() throws Exception {
		log.log(Level.INFO, "EntityManagerFactorySingleton Stating");
		EntityManagerFactorySingleton.getInstance().start();
		intanceToDo = new MessageFacadeImpl();
		intanceToDo.setEntityManager(EntityManagerFactorySingleton.getInstance().getManager());
	}

	
	@Test(groups = "MaSuite")
	public void testCreateByDiscussion() throws Exception {
		log.log(Level.INFO, "#### >> testCreateByDiscussion");
		try {
			DiscussionFacadeImpl discussionFacade= new DiscussionFacadeImpl();
			discussionFacade.setEntityManager(EntityManagerFactorySingleton.getInstance().getManager());
			assertNotNull(intanceToDo);
			Discussion discussion = new Discussion();
			discussion.setLeTitre("mon titre à moi !");
			discussion.setLeTexte("le grand texte.");

			Message msg=new Message();
			msg.setLeTexte("mon message");
			discussion.getMessages().add(msg);
			msg.setDiscussion(discussion);
			discussionFacade.create(discussion);

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testCreateByMessage() throws Exception {
		log.log(Level.INFO, "#### >> testCreateByMessage");
		try {
			DiscussionFacadeImpl discussionFacade= new DiscussionFacadeImpl();
			discussionFacade.setEntityManager(EntityManagerFactorySingleton.getInstance().getManager());
			assertNotNull(discussionFacade);
			MessageFacadeImpl facade = new MessageFacadeImpl();
			facade.setEntityManager(EntityManagerFactorySingleton.getInstance().getManager());
			assertNotNull(facade);
			
			List<Discussion> discussions =discussionFacade.findAll();
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
