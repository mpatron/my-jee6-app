package org.jobjects.derby;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EntityManagerFactoryStart {
	private static Logger LOGGER = Logger.getLogger(EntityManagerFactoryStart.class.getName());
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			EntityManagerFactorySingleton.getInstance().start();
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE, "Erreur non prévu : ", e);
			e.printStackTrace();
		}
	}

	@Test(groups = "MaSuite")
	public void testStart() {
		LOGGER.log(Level.INFO, "EntityManagerFactory Stating");
	}

}
