package org.jobjects.orm.tools;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUUIDBaseEntity implements Serializable {
	protected static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UUID_ID", nullable = false, length=36)
	protected String id;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public AbstractUUIDBaseEntity() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractUUIDBaseEntity)) {
			return false;
		}
		AbstractUUIDBaseEntity other = (AbstractUUIDBaseEntity) obj;
		return getId().equals(other.getId());
	}
}