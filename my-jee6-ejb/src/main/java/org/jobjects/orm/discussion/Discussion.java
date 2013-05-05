package org.jobjects.orm.discussion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jobjects.orm.message.Message;
import org.jobjects.orm.tools.AbstractUUIDBaseEntity;

@Entity
@Table(name = "DISCUSSIONS"/*, schema = "AAJPA"*/)
@NamedQueries({
		@NamedQuery(name = "findDiscussionByDiscussionId", query = "select t from Discussion t where t.id = :discussionId"),
		@NamedQuery(name = "findAllDiscussion", query = "select t from Discussion t") })
public class Discussion extends AbstractUUIDBaseEntity implements Serializable {

	public Discussion() {
		super();
		setDateCreation(new Date(System.currentTimeMillis()));
	}

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "discussion", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Message> messages=new ArrayList<Message>();
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateMiseAJour;
	
	@Column(name = "LE_TITRE", nullable = false)
	private String leTitre;
	
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
	 * @return the leTitre
	 */
	public String getLeTitre() {
		return leTitre;
	}

	/**
	 * @param leTitre the leTitre to set
	 */
	public void setLeTitre(String leTitre) {
		this.leTitre = leTitre;
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
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
