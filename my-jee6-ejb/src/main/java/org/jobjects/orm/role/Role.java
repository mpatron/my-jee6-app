package org.jobjects.orm.role;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "Role")
@Table(name = "ROLES")
public class Role implements Serializable {

	public Role() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 255)
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}