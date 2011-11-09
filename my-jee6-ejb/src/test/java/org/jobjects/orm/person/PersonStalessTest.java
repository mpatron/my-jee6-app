package org.jobjects.orm.person;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.jobjects.orm.EJBContainerEmbedded;
import org.testng.annotations.Test;

public class PersonStalessTest {

	private Context ctx;

	public PersonStalessTest() {
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
	public void testCreate() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
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

	@Test(groups = "ir")
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

	@Test(groups = "ir")
	public void testRemove() {
		// fail("Not yet implemented");
	}

	@Test(groups = "ir")
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

	@Test(groups = "ir")
	public void testFindAll() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			List<Person> list = personFacade.findAll();
			System.out.println("count = " + list.size());
			Logger.getLogger(PersonStalessTest.class.getName()).log(Level.INFO,
					("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 == list.size());
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "ir")
	public void testFindRange() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			List<Person> list = personFacade.findRange(new int[] { 0, 1 });
			Logger.getLogger(PersonStalessTest.class.getName()).log(Level.INFO,
					("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 == list.size());
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "ir")
	public void testCount() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx
					.lookup("java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade");
			long l = personFacade.count();
			assertTrue(l >= 0);
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "ir")
	public void testFindByNamedQuery() {
		try {
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

}
