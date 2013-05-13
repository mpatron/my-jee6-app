package org.jobjects.derby;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EntityManagerFactoryStop {
	private static Logger LOGGER = Logger.getLogger(EntityManagerFactoryStop.class.getName());
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			EntityManagerFactorySingleton.getInstance().stop();
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE, "Erreur non prévu : ", e);
		}
	}

	@Test(groups = "MaSuite")
	public void testStart() {
		LOGGER.log(Level.INFO, "EntityManagerFactory Stop");
	}
}
