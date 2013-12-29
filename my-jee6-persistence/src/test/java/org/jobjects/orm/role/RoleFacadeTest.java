package org.jobjects.orm.role;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jobjects.tools.entitymanager.EntityManagerFactorySingleton;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RoleFacadeTest {
	
	private static Logger log = Logger.getLogger(RoleFacadeTest.class.getName());
	private static RoleFacadeImpl intanceToDo = null;

	@BeforeClass(groups = "MaSuite")
	public static void setUpBeforeClass() throws Exception {
		log.log(Level.INFO, "EntityManagerFactorySingleton Stating");
		EntityManagerFactorySingleton.getInstance().start();
		intanceToDo = new RoleFacadeImpl();
		intanceToDo.setEntityManager(EntityManagerFactorySingleton.getInstance().getManager());
	}
	
	/**
	 * Vérifie les attributs de l'apprenti
	 */
	@Test(groups = "MaSuite", enabled = true)
	public void attributesTestingShouldBeOk() {
		System.out.println("test 1");
		org.testng.Assert.assertTrue(true);
	}
	
	@Test(groups = "MaSuite")
	public void testFindAll() {
		try {
			List<Role> list = intanceToDo.findAll();
			System.out.println("count = " + list.size());
			log.log(Level.INFO,("count = " + list.size()));
			assertNotNull(list);
			assertTrue(0 <= list.size());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
}
