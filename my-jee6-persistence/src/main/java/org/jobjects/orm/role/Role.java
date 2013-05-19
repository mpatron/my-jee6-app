package org.jobjects.orm.role;

import java.io.Serializable;

import javax.persistence.*;

import org.jobjects.orm.tools.AppConstants;

@Entity(name = "Role")
@Table(name = "ROLES", schema = AppConstants.SCHEMA_NAME)
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

	@Column(length = 255)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}