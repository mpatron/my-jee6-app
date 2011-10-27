package org.glassfish.samples;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.jobjects.orm.User;
import org.jobjects.orm.Users;

@ManagedBean(name = "simplebean")
public class SimpleBean {

	// @EJB(lookup = "java:global/classes/Users!org.jobjects.orm.Users")
	//@EJB(lookup = "java:global/ear-1.0/ejb-1.0/UsersStateless!org.jobjects.orm.Users")
	//@EJB(lookup = "java:global/ear-1.0/ejb-1.0/UsersStateless")
	@EJB()
	Users u;
	
	//@EJB() PersonsStaless p;

	public SimpleBean() {
		/*
		 * Context ctx = null; try { ctx = new InitialContext(); // UsersImpl uu
		 * = (UsersImpl) //
		 * ctx.lookup("java:global/classes/Users!org.jobjects.orm.Users"); Users
		 * uu = (Users) ctx .lookup(
		 * "java:global/ear-1.0/ejb-1.0/UsersStateless!org.jobjects.orm.Users");
		 * uu.findAllUserByFirstName("toto"); } catch (Throwable ex) {
		 * Logger.getLogger(SimpleBean.class.getName()).log(Level.SEVERE, null,
		 * ex); } Logger.getLogger(SimpleBean.class.getName()).log(Level.INFO,
		 * "Fin de l'application");
		 */
	}

	@NotNull
	private String name;
	@DecimalMax(value = "24")
	private int age;

	public String getName() {
		try {
			List<User> l= u.findAllUserByFirstName("toto");
			name +=l.size();
		} catch (Throwable ex) {
			Logger.getLogger(SimpleBean.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
