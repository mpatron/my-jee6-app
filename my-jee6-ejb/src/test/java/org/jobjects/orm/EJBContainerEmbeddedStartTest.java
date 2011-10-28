package org.jobjects.orm;

import org.testng.annotations.Test;

public class EJBContainerEmbeddedStartTest {
	
	@Test(groups = "ir", enabled = true)
	public void startGlassfish() {
		System.out.println(" ====================================== ");
		System.out.println(" =         START GLASSFISH            = ");
		System.out.println(" ====================================== ");
		EJBContainerEmbedded.getInstance().getEJBContainer();
	}

}
