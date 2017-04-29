package com.mithyber.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mithyber.messenger.database.DatabaseClass;
import com.mithyber.messenger.model.Comment;
import com.mithyber.messenger.model.ErrorMessage;
import com.mithyber.messenger.model.Message;

public class CommentService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId) {
	Map<Long, Comment> comments = messages.get(messageId)
		.getComments();
	return new ArrayList<>(comments.values());
    }

    // using exception that jax-rs knows how to handle instead of creating mapper to custom exception
    // kinda bad to place it inside business logic
    // there is a bundle of special exceptions in WebApplicationException hierarchy already - like NotFoundException
    public Comment getComment(long messageId, long commentId) {
	ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "there is no URI to documentation");
	Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
		.type(MediaType.APPLICATION_JSON)
		.entity(errorMessage)
		.build();
	Message message = messages.get(messageId);
	if (message == null)
	    throw new WebApplicationException(response);
	Map<Long, Comment> comments = message.getComments();
	Comment comment = comments.get(commentId);
	if (comment == null)
	    throw new WebApplicationException(response);
	return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
	Map<Long, Comment> comments = messages.get(messageId)
		.getComments();
	comment.setId(comments.size() + 1);
	comments.put(comment.getId(), comment);
	return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
	Map<Long, Comment> comments = messages.get(messageId)
		.getComments();
	if (comment.getId() <= 0)
	    return null;
	comments.put(comment.getId(), comment);
	return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
	Map<Long, Comment> comments = messages.get(messageId)
		.getComments();
	return comments.remove(commentId);
    }
}
