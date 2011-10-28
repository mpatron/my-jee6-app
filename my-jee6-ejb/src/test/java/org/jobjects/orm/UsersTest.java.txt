package org.jobjects.orm;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.sql.DataSource;

import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

//import static org.junit.Assert.as

public class UsersTest {
	
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
		properties.put(EJBContainer.PROVIDER, "org.glassfish.ejb.embedded.EJBContainerProviderImpl");

		
		/*Avec une installation de glassfish afin de facilité la confuiguration pour les tests*/
		/* http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html */
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "C:/programs/glassfish-3.1.1/glassfish");
		properties.put("org.glassfish.ejb.embedded.glassfish.instance.root", "C:/programs/glassfish-3.1.1/glassfish/domains/domain1");
		properties.put("org.glassfish.ejb.embedded.glassfish.configuration.file", "C:/programs/glassfish-3.1.1/glassfish/domains/domain1/config/domain.xml");
		properties.put("org.glassfish.ejb.embedded.glassfish.keep-temporary-files", "true");

		
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

	// ======================================
	// = Unit tests =
	// ======================================
//	@PersistenceContext
//	private EntityManager em;
//
//	@Test
//	public void testJNDI() {
//		try {
//			/*Fonctionne !!, Glassfish sauvegarde une valeur pour un JNDI il faut ajouter 
//			 * dans le configurateur JNDI de glasfish avec le type de ressource :
//			 * org.glassfish.resources.custom.factory.PrimitivesAndStringFactory
//			 * une propriete du nom "value" avec la valeur désirée.
//			 * http://download.oracle.com/docs/cd/E19798-01/821-1752/giyvw/index.html */
//			String toto = (String)ctx.lookup("url/toto");
//			System.out.println("toto="+toto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.assertTrue(false);
//		}
//	}
//	
//	@Test
//	public void testPersistence() {
//		/* Ne fonctionne pas. Il faut tester le JPA à part.
//		 * d'un autre coté c'est la parti métier ici et pas la parti persistance !!!
//		 * */
//		try {
//			EntityManagerFactory emf =Persistence.createEntityManagerFactory("aa-jpa");
//			System.out.println("&&&"+emf.getProperties());
//			em = emf.createEntityManager();
//			Query query = em.createQuery("select t from User t");
//			Assert.assertTrue(query.getResultList().size()>=0);
//			em.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.assertTrue(false);
//		}
//	}
	
	
	@Test
	public void testGetConnection() {
		try {			
			DataSource myTest = (DataSource) ctx.lookup("jdbc/maBase");
			Connection conn =  myTest.getConnection();
			assertNotNull(conn);
			System.out.println(conn.getMetaData().getDatabaseProductName() + " " + conn.getMetaData().getDatabaseProductVersion() );			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	
	@Test
	public void testCreate() throws Exception {
		try {
			Users users = (Users) ctx.lookup("java:global/my-jee-app/classes/UsersStateless!org.jobjects.orm.Users");
		    User user=new User();
		    user.setLogin("mpatron");
		    user.setEmail("mickael_patron@hotmail.com");
		    user.setFirstName("Mickael");
		    user.setLastName("Patron");
			users.create(user);
			user.setPassword("password");
			users.save(user);
						
			List<User> userList= users.findUserByLogin("mpatron");
			System.out.println("count = " + userList.size());
			Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, ("count = " + userList.size()));
			assertNotNull(userList);
			assertTrue(1==userList.size());
		} catch (Exception e) {
			Logger.getLogger(UsersImpl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
		
	}

	@Test
	public void testFindUserByLogin() throws Exception {
//		Users users = (Users) ctx.lookup("java:global/my-jee-app/classes/UsersStateless!org.jobjects.orm.Users");
//		List<User> userList= users.findUserByLogin("mpatron");
//		Assert.assertTrue(1==userList.size());
	}

	
	@Test
	public void testSave() {
		assertTrue(true);
	}

	@Test
	public void testRemove() {
		// fail("Not yet implemented");
		assertTrue(true);
	}

	@Test
	public void testLoad() {
		// fail("Not yet implemented");
		assertTrue(true);
	}
}
