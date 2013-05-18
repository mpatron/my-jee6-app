package org.jobjects.orm.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.Test;

public class EJBContainerEmbeddedStartTest {
	private static Logger LOGGER = Logger.getLogger(EJBContainerEmbeddedStartTest.class
			.getName());

	@Test(groups = "ir", enabled = true)
	public void startGlassfish() {
		LOGGER.log(Level.INFO, " ====================================== ");
		LOGGER.log(Level.INFO, " =          START GLASSFISH           = ");
		LOGGER.log(Level.INFO, " ====================================== ");
		EJBContainerEmbedded.getInstance().getEJBContainer();
	}

}
