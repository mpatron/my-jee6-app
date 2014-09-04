package org.jobjects.tools.ejbcontainer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.BeforeSuite;

public class EJBContainerEmbeddedStartTest {
	private static Logger LOGGER = Logger.getLogger(EJBContainerEmbeddedStartTest.class.getName());

	//@Test(groups = "ir", enabled = true)
	@BeforeSuite(groups = "MaSuite", enabled = true)
	public void startGlassfish() {
		LOGGER.log(Level.INFO, " ====================================== ");
		LOGGER.log(Level.INFO, " =          START OPENEJB             = ");
		LOGGER.log(Level.INFO, " ====================================== ");
		EJBContainerEmbedded.getInstance().getEJBContainer();
	}

}