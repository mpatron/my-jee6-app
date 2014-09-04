package org.jobjects.tools.ejbcontainer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.AfterSuite;

public class EJBContainerEmbeddedStopTest {
	private static Logger LOGGER = Logger.getLogger(EJBContainerEmbeddedStopTest.class
			.getName());
	
	//@Test(groups = "ir", enabled = true)
	@AfterSuite(groups = "MaSuite", enabled = true)
	public void startGlassfish() {
		LOGGER.log(Level.INFO, " ====================================== ");
		LOGGER.log(Level.INFO, " =          STOP OPENEJB              = ");
		LOGGER.log(Level.INFO, " ====================================== ");
		try {
			EJBContainerEmbedded.getInstance().shutdown();
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

}