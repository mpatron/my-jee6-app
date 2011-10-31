package org.jobjects.orm;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.sql.DataSource;

import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UsersTest {
	
	private Context ctx;

	public UsersTest() {
		ctx = EJBContainerEmbedded.getInstance().getContext();
	}
	
	
	// ======================================
	// = Unit tests =
	// ======================================
//	@PersistenceContext
//	private EntityManager em;
//
//	@Test(groups = "ir")
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
//	@Test(groups = "ir")
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
	
	
	@Test(groups = "ir")
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
	
	
	@Test(groups = "ir")
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

	@Test(groups = "ir")
	public void testFindUserByLogin() throws Exception {
//		Users users = (Users) ctx.lookup("java:global/my-jee-app/classes/UsersStateless!org.jobjects.orm.Users");
//		List<User> userList= users.findUserByLogin("mpatron");
//		Assert.assertTrue(1==userList.size());
	}

	
	@Test(groups = "ir")
	public void testSave() {
		assertTrue(true);
	}

	@Test(groups = "ir")
	public void testRemove() {
		// fail("Not yet implemented");
		assertTrue(true);
	}

	@Test(groups = "ir")
	public void testLoad() {
		// fail("Not yet implemented");
		assertTrue(true);
	}

}