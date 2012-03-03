package org.safaproject.safa.model.content;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.safaproject.safa.model.user.User;

@Entity
@Table(name = "COMMENT")
public class Comment {

	public Comment() {
		//Constructor for hibernate
	}
	
	public Comment(Date commentDate, User user, String commentText) {
		super();
		this.commentDate = commentDate;
		this.user = user;
		this.commentText = commentText;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentId")
	private Long commentId;

	@Column(name = "commentDate")
	private Date commentDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "commentText")
	private String commentText;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

}
