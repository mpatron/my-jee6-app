package org.jobjects.orm.discussion;

import static org.testng.Assert.assertNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jobjects.tools.entitymanager.EntityManagerFactorySingleton;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DiscussionFacadeTest {
	private static Logger log = Logger.getLogger(DiscussionFacadeTest.class
			.getName());

	private static DiscussionFacadeImpl intanceToDo = null;

	@BeforeClass(groups = "MaSuite")
	public static void setUpBeforeClass() throws Exception {
		log.log(Level.INFO, "EntityManagerFactorySingleton Stating");
		EntityManagerFactorySingleton.getInstance().start();
		intanceToDo = new DiscussionFacadeImpl();
		intanceToDo.setEntityManager(EntityManagerFactorySingleton.getInstance().getManager());
	}

	@Test(groups = "MaSuite")
	public void testCreate() throws Exception {
		log.log(Level.INFO, "#### >> testCreate");
		try {
			assertNotNull(intanceToDo);

			Discussion discussion = new Discussion();
			discussion.setLeTitre("mon titre à moi !");
			discussion.setLeTexte("le grand texte.");
			intanceToDo.create(discussion);

			assertNotNull(intanceToDo.findAll());

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
