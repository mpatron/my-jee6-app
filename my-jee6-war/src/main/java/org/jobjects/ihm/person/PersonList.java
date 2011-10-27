package org.jobjects.ihm.person;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jobjects.orm.person.Person;
import org.jobjects.orm.person.PersonFacade;

@ManagedBean(name = "personList")
public class PersonList {
	//@EJB(lookup = "java:global/ear-1.0/ejb-1.0/UsersStateless")
	@EJB()
	PersonFacade p;
	public PersonList() {		
	}
	
	public List<Person> getListAll() {
		return p.findAll();
	}
	
	public String doDelete() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String loginName = req.getParameter("loginName");
		Person person=p.find(loginName);
		p.remove(person);
		return null;
	}
	
	
	public String doEdit() {
		return "person.xhtml";
	}
}
