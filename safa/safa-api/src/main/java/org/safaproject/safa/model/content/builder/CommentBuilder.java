package org.safaproject.safa.model.content.builder;

import java.util.Date;

import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.user.User;

import com.google.common.base.Preconditions;

/**
 * Builder for the Comment class. Must be reviewed if the object is built at the
 * end of the process (build method), or at the beggining and then just fill the
 * fields with setters. (This solution needs an empty constructor for Comment)
 * 
 * @author Chaito
 * 
 */
public class CommentBuilder {

	private User user;

	private Date commentDate;

	private String commentText;

	public CommentBuilder withUser(User user) {
		this.user = user;
		return this;
	}

	public CommentBuilder withCommentDate(Date date) {
		this.commentDate = date;
		return this;
	}

	public CommentBuilder withCommentText(String commentText) {
		this.commentText = commentText;
		return this;
	}

	public Comment build() {
		Preconditions.checkNotNull(user);
		Preconditions.checkNotNull(commentDate);
		Preconditions.checkNotNull(commentText);
		
		return new Comment(commentDate, user, commentText);
	}
}
