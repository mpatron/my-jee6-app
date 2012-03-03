package org.jobjects.orm.person;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jobjects.orm.role.Role;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@Table(name = "PERSONS"/*, schema = "AAJPA"*/)
@NamedQueries({
		@NamedQuery(name = "findPersonByLogin", query = "select t from Person t where t.login = :login"),
		@NamedQuery(name = "findAllPersonByFirstName", query = "select t from Person t where t.firstName = :firstName") })
public class Person implements Serializable {

	public Person() {
		super();
	}

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 255)
	private String login;
	@Column(length = 512)
	private String password;
	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;
	@Column
	@Temporal(TemporalType.DATE)
	private Date birthDate;


	/*
	 * Le RFC 3696 limite la partie gauche à 64 octets au maximum, soit un total
	 * de 320 octets au maximum pour l'adresse complète (64+1+255).
	 */
	@Column(length = 320)
	private String email;

	@ManyToMany()
	@JoinTable(name="persons_roles")
	private List<Role> role;

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRole() {
	    return role;
	}

	public void setRole(List<Role> param) {
	    this.role = param;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
