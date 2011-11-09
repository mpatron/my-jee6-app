package org.jobjects.orm.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.Test;

public class EJBContainerEmbeddedStopTest {

	@Test(groups = "ir", enabled = true)
	public void startGlassfish() {
		System.out.println(" ====================================== ");
		System.out.println(" =          STOP GLASSFISH            = ");
		System.out.println(" ====================================== ");

		try {
			EJBContainerEmbedded.getInstance().shutdown();
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
