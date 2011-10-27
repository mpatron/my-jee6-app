package org.jobjects.orm;

import java.util.List;

import javax.ejb.Local;

@Local
public interface Users extends Manager<User, String> {
	void create(User bean) throws Exception;

	User save(User bean) throws Exception;

	void remove(User bean) throws Exception;

	User load(String beanPk) throws Exception;

	public List<User> findUserByLogin(String login);

	public List<User> findAllUserByFirstName(String firstName);
}
