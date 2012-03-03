package org.jobjects.ihm.person;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.jobjects.orm.person.Person;
import org.jobjects.orm.person.PersonFacade;

@ManagedBean(name = "person")
public class PersonManagedBean {
	//(lookup = "java:global/my-jee6-ear-1.0/my-jee6-ejb-1.0/PersonStaless")
	@EJB()
	PersonFacade p;

	public PersonManagedBean() {
	}

	@PostConstruct
	protected void populate() {
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String loginName = req.getParameter("loginName");
			if (loginName != null && !"".equals(loginName)) {
				Person person = p.find(loginName);
				this.login = person.getLogin();
				this.firstName = person.getFirstName();
				this.lastName = person.getLastName();
				this.password = person.getPassword();
				this.birthDate = person.getBirthDate();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Size(max = 255)
	@NotNull
	private String login;
	@Size(max = 512)
	private String password;
	@Size(max = 50)
	private String firstName;
	@Size(max = 50)
	private String lastName;	
	@Past
	private Date birthDate;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String doUpdate() {
		Person user = new Person();
		user.setLogin(login);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setBirthDate(birthDate);
		 if(null==p.find(login)) {
			 p.create(user);
		 } else {
			 p.save(user);
		 }
		
		return "persons.xhtml";
	}
}
