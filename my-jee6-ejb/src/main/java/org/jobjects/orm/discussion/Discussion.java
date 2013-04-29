package org.jobjects.orm.discussion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jobjects.orm.message.Message;

@Entity
@Table(name = "DISCUSSIONS"/*, schema = "AAJPA"*/)
@NamedQueries({
		@NamedQuery(name = "findDiscussionByDiscussionId", query = "select t from Discussion t where t.discussionId = :discussionId"),
		@NamedQuery(name = "findAllDiscussion", query = "select t from Discussion t") })
public class Discussion implements Serializable {

	public Discussion() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DISCUSSION_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer discussionId;

	@OneToMany(mappedBy = "messageId", cascade = { CascadeType.ALL })
	private Set<Message> messages = new HashSet<Message>();
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateMiseAJour;
	
}
