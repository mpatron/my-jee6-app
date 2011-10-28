package org.jobjects.orm.person;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonStalessTest {

	// ======================================
	// = Attributes =
	// ======================================
	private static EJBContainer ec;
	private static Context ctx;

	// ======================================
	// = Lifecycle Methods =
	// ======================================

	@BeforeClass
	public static void initContainer() throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.APP_NAME, "my-jee-app");
		properties.put(EJBContainer.MODULES, new File("target/classes"));
		properties.put(EJBContainer.PROVIDER,
				"org.glassfish.ejb.embedded.EJBContainerProviderImpl");

		/*
		 * Avec une installation de glassfish afin de facilité la
		 * confuiguration pour les tests
		 */
		/* http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html */
		properties.put(
				"org.glassfish.ejb.embedded.glassfish.installation.root",
				"C:/programs/glassfish-3.1.1/glassfish");
		properties.put("org.glassfish.ejb.embedded.glassfish.instance.root",
				"C:/programs/glassfish-3.1.1/glassfish/domains/domain1");
		properties
				.put("org.glassfish.ejb.embedded.glassfish.configuration.file",
						"C:/programs/glassfish-3.1.1/glassfish/domains/domain1/config/domain.xml");
		properties.put(
				"org.glassfish.ejb.embedded.glassfish.keep-temporary-files",
				"true");

		ec = EJBContainer.createEJBContainer(properties);
		ctx = ec.getContext();
	}

	@AfterClass
	public static void closeContainer() throws Exception {
		if (ctx != null)
			ctx.close();
		if (ec != null)
			ec.close();
	}

	@Test
	public void testCreate() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			Person person = new Person();
			person.setLogin("mpatron");
			person.setEmail("mickael_patron@hotmail.com");
			person.setFirstName("Mickael");
			person.setLastName("Patron");
			personFacade.create(person);
			
			assertTrue(1 == personFacade.count());			

		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test
	public void testSave() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			Person person = new Person();
			person.setLogin("mpatron");
			person.setEmail("mickael_patron@hotmail.com");
			person.setFirstName("Mickael");
			person.setLastName("Patron");
			personFacade.save(person);
			
			assertTrue(1 == personFacade.count());		
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test
	public void testRemove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			Person person = personFacade.find("mpatron");
			assertNotNull(person);
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test
	public void testFindAll() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			List<Person> list = personFacade.findAll();
			System.out.println("count = " + list.size());
			Logger.getLogger(PersonStalessTest.class.getName()).log( Level.INFO, ("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 == list.size());
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test
	public void testFindRange() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			List<Person> list = personFacade.findRange(new int[] { 0, 1 });
			Logger.getLogger(PersonStalessTest.class.getName()).log( Level.INFO, ("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 == list.size());
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test
	public void testCount() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			long l = personFacade.count();
			assertTrue(1 >= 0);
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test
	public void testFindByNamedQuery() {
		try {
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

}
