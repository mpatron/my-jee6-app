package org.jobjects.orm.message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jobjects.orm.discussion.Discussion;
import org.jobjects.orm.tools.AbstractUUIDBaseEntity;
import org.jobjects.orm.tools.AppConstants;

@Entity
@Table(name = "MESSAGES", schema = AppConstants.SCHEMA_NAME)
@NamedQueries({
		@NamedQuery(name = "findMessageByMessageId", query = "select t from Message t where t.id = :messageId"),
		@NamedQuery(name = "findAllMessage", query = "select t from Message t") })
public class Message extends AbstractUUIDBaseEntity implements Serializable {
	public Message() {
		super();
		setDateCreation(new Date(System.currentTimeMillis()));
	}

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISCUSSION_ID", nullable = false)/*UUID_ID vient de discussion */
	private Discussion discussion;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateMiseAJour;

	@Column(name = "LE_TEXTE", nullable = false)
	private String leTexte;

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateMiseAJour
	 */
	public Date getDateMiseAJour() {
		return dateMiseAJour;
	}

	/**
	 * @param dateMiseAJour the dateMiseAJour to set
	 */
	public void setDateMiseAJour(Date dateMiseAJour) {
		this.dateMiseAJour = dateMiseAJour;
	}

	/**
	 * @return the leTexte
	 */
	public String getLeTexte() {
		return leTexte;
	}

	/**
	 * @param leTexte the leTexte to set
	 */
	public void setLeTexte(String leTexte) {
		this.leTexte = leTexte;
	}
	
	/**
	 * @return the discussion
	 */
	public Discussion getDiscussion() {
		return discussion;
	}

	/**
	 * @param discussion the discussion to set
	 */
	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
}
