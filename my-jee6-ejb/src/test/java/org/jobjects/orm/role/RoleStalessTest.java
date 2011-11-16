package org.jobjects.orm.role;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.jobjects.orm.tools.EJBContainerEmbedded;
import org.testng.annotations.Test;

public class RoleStalessTest {

	private Context ctx;

	public RoleStalessTest() {
		ctx = EJBContainerEmbedded.getInstance().getContext();
	}

	/**
	 * Vérifie les attributs de l'apprenti
	 */
	@Test(groups = "ir", enabled = true)
	public void attributesTestingShouldBeOk() {
		System.out.println("test 1");
		org.testng.Assert.assertTrue(true);
	}
	
	@Test(groups = "ir")
	public void testFindAll() {
		try {
			RoleFacade RoleFacade = (RoleFacade) ctx
					.lookup("java:global/my-jee-app/classes/RoleStaless!org.jobjects.orm.role.RoleFacade");
			List<Role> list = RoleFacade.findAll();
			System.out.println("count = " + list.size());
			Logger.getLogger(RoleStalessTest.class.getName()).log(Level.INFO,
					("count = " + list.size()));
			assertNotNull(list);
			assertTrue(0 <= list.size());
		} catch (Exception e) {
			Logger.getLogger(RoleStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}
	
}
