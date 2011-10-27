package org.jobjects.orm.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

	@Id
	@Column(length = 255)
	private String login;
	@Column(length = 512)
	private String password;
	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;

	/*
	 * Le RFC 3696 limite la partie gauche à 64 octets au maximum, soit un total
	 * de 320 octets au maximum pour l'adresse complète (64+1+255).
	 */
	@Column(length = 320)
	private String email;
	private static final long serialVersionUID = 1L;

	public Person() {
		super();
	}

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

}
