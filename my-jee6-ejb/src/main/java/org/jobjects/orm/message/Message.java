package org.jobjects.orm.message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jobjects.orm.discussion.Discussion;

@Entity
@Table(name = "MESSAGES"/* , schema = "AAJPA" */)
@NamedQueries({
		@NamedQuery(name = "findMessageByMessageId", query = "select t from Message t where t.messageId = :messageId"),
		@NamedQuery(name = "findAllMessage", query = "select t from Message t") })
public class Message  implements Serializable {
	public Message() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MESSAGE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long messageId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "DISCUSSION_ID", nullable = false)
	private Discussion discussion;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateMiseAJour;
	
	@Column(name = "LE_TEXTE", nullable = false)
	private String leTexte;
}

