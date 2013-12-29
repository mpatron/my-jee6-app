package org.jobjects.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.Test;

public class MonTest {
	private Logger log = Logger.getLogger(getClass().getName());

	@Test(groups = "MaSuite")
	public void f() {
		log.severe("mon test ok.");
		log.log(Level.SEVERE, "!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("<<<<<<<<<<");
	}
}
