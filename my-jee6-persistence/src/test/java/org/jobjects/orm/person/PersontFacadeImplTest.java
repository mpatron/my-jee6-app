package org.jobjects.orm.person;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityTransaction;

import org.jobjects.derby.EntityManagerFactorySingleton;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PersontFacadeImplTest {

	private static Logger log = Logger.getLogger(PersontFacadeImplTest.class
			.getName());
	private static PersontFacadeImpl intanceToDo = null;

	@BeforeClass(groups = "MaSuite")
	public static void setUpBeforeClass() throws Exception {
		log.log(Level.INFO, "EntityManagerFactorySingleton Stating");
		EntityManagerFactorySingleton.getInstance().start();
		intanceToDo = new PersontFacadeImpl();
		intanceToDo.entityManager = EntityManagerFactorySingleton.getInstance()
				.getManager();
	}

	public PersontFacadeImplTest() {
	}

	@Test(groups = "MaSuite")
	public void PersontFacadeImpl() {
		assertNotNull(new PersontFacadeImpl());
	}

	@Test(groups = "MaSuite")
	public void getEntityManager() {
		assertNotNull(intanceToDo.getEntityManager());
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
	public void regexTesting() {
		log.log(Level.INFO,
				"regexTesting ^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
		String email = "mickael_patron@hotmail.com";
		Matcher m = Pattern.compile(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")
				.matcher(email);
		boolean essai = m.find();
		assertTrue(essai);

	}

	@Test(groups = "MaSuite")
	public void testCreate() throws Exception {
		Logger.getLogger(getClass().getName()).log(Level.INFO,
				"#### >> testCreate");
		// try {

		if (intanceToDo.find("mpatron") != null) {
			Person key = new Person();
			key.setLogin("mpatron");
			intanceToDo.remove(key);
		}

		Person person = new Person();
		person.setLogin("mpatron");
		person.setEmail("mickael_patron@hotmail.com");
		person.setFirstName("Mickael");
		person.setLastName("Patron");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		person.setBirthDate(sdf.parse("29/02/2012"));
		intanceToDo.create(person);

		assertNotNull(intanceToDo.find("mpatron"));
		/*
		 * } catch (Exception e) {
		 * Logger.getLogger(PersonStalessTest.class.getName()).log(
		 * Level.SEVERE, e.getMessage(), e); }
		 */
	}

	@Test(groups = "MaSuite")
	public void testSave() {
		try {
			Person person = new Person();
			person.setLogin("mpatron");
			person.setEmail("mickael_patron@hotmail.com");
			person.setFirstName("Mickaël");
			person.setLastName("Patron");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			person.setBirthDate(sdf.parse("29/02/2012"));
			intanceToDo.save(person);

			Person person2 = intanceToDo.find("mpatron");
			assertNotNull(person2);
			if (null != person2) {
				assertEquals(person.getLogin(), person2.getLogin());
				assertEquals(person.getEmail(), person2.getEmail());
				assertEquals(person.getFirstName(), person2.getFirstName());
				assertEquals(person.getLastName(), person2.getLastName());
			} else {
				throw new Exception("Impossible");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testRemove() {
		// fail("Not yet implemented");
	}

	@Test(groups = "MaSuite")
	public void testFind() {
		try {
			Person person = intanceToDo.find("mpatron");
			assertNotNull(person);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testFindAll() {
		try {
			List<Person> list = intanceToDo.findAll();
			System.out.println("count = " + list.size());
			log.log(Level.INFO, ("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 <= list.size());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testFindRange() {
		try {
			List<Person> list = intanceToDo.findRange(new int[] { 0, 1 });
			log.log(Level.INFO, ("count = " + list.size()));
			assertNotNull(list);
			assertTrue(1 == list.size());
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testCount() {
		try {
			long l = intanceToDo.count();
			assertTrue(l >= 0);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Test(groups = "MaSuite")
	public void testFindByNamedQuery() {
		try {
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
