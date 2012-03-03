package org.jobjects.orm.person;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import org.jobjects.orm.tools.EJBContainerEmbedded;
import org.testng.annotations.Test;

public class PersonStalessTest {

	//private String jndiStaless = "java:global/my-jee-app/classes/PersonsStaless!org.jobjects.orm.person.PersonFacade";
	private String jndiStaless = "java:global/my-jee-app/classes/PersonStaless";
	
	private Context ctx;

	public PersonStalessTest() {
		ctx = EJBContainerEmbedded.getInstance().getContext();
	}

	/**
	 * Vérifie les attributs de l'apprenti
	 */
	@Test(groups = "MaSuite", enabled = true)
	public void attributesTestingShouldBeOk() {
		System.out.println("test 1");
		org.testng.Assert.assertTrue(true);
	}

	@Test(groups = "MaSuite")
	public void testCreate() throws Exception {
		Logger.getLogger(getClass().getName()).log(Level.INFO, "#### >> testCreate");
		//try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup(jndiStaless);
			
			if(personFacade.find("mpatron") != null ) {
				Person key = new Person();
				key.setLogin("mpatron");
				personFacade.remove(key);
			}			
			
			Person person = new Person();
			person.setLogin("mpatron");
			person.setEmail("mickael_patron@hotmail.com");
			person.setFirstName("Mickael");
			person.setLastName("Patron");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;			
			person.setBirthDate(sdf.parse("29/02/2012"));
			personFacade.create(person);

			assertNotNull(personFacade.find("mpatron"));
/*
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
*/
	}

	@Test(groups = "MaSuite")
	public void testSave() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup(jndiStaless);
			Person person = new Person();
			person.setLogin("mpatron");
			person.setEmail("mickael_patron@hotmail.com");
			person.setFirstName("Mickaël");
			person.setLastName("Patron");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;			
			person.setBirthDate(sdf.parse("29/02/2012"));
			personFacade.save(person);

			Person person2 = personFacade.find("mpatron");
			assertNotNull(person2);
			if(null!=person2) {
				assertEquals(person.getLogin(), person2.getLogin());
				assertEquals(person.getEmail(), person2.getEmail());
				assertEquals(person.getFirstName(), person2.getFirstName());
				assertEquals(person.getLastName(), person2.getLastName());
			}
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testRemove() {
		// fail("Not yet implemented");
	}

	@Test(groups = "MaSuite")
	public void testFind() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup(jndiStaless);
			Person person = personFacade.find("mpatron");
			assertNotNull(person);
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testFindAll() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup(jndiStaless);
			List<Person> list = personFacade.findAll();
			System.out.println("count = " + list.size());
			Logger.getLogger(PersonStalessTest.class.getName()).log(Level.INFO,
					("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 <= list.size());
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testFindRange() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup(jndiStaless);
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

	@Test(groups = "MaSuite")
	public void testCount() {
		try {
			PersonFacade personFacade = (PersonFacade) ctx.lookup(jndiStaless);
			long l = personFacade.count();
			assertTrue(l >= 0);
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testFindByNamedQuery() {
		try {
		} catch (Exception e) {
			Logger.getLogger(PersonStalessTest.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
	}

}
