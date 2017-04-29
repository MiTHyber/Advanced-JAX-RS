package com.mithyber.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.mithyber.messenger.model.Message;
import com.mithyber.messenger.resources.beans.MessageFilterBean;
import com.mithyber.messenger.service.MessageService;

@Path("/messages")
// if u have same consumes and produces than u can just annotate entire class
@Consumes(MediaType.APPLICATION_JSON)
// multiple return types
// @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    // @GET
    // @Produces(MediaType.APPLICATION_XML)
    // json works with jaxb - so we don't need to change anything - only add dependency to something that can convert to
    // and from JSON like jersey-media-moxy
    // @Produces(MediaType.APPLICATION_JSON)
    // public List<Message> getMessages@QueryParam("year") Integer year, @QueryParam("start") Integer start,
    // @QueryParam("size") Integer size) {
    // if (year != null)
    // return messageService.getAllMessagesForYear(year);
    // if (start != null && size != null && start >= 0 && size >= 0)
    // return messageService.getAllMessagesPaginated(start, size);
    // return messageService.getAllMessages();
    // }
    // if u have a lot of params u might want to use BeanParam instead
    // public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean) {
    // if (messageFilterBean.getYear() != null)
    // return messageService.getAllMessagesForYear(messageFilterBean.getYear());
    // if (messageFilterBean.getStart() != null && messageFilterBean.getSize() != null && messageFilterBean
    // .getStart() >= 0 && messageFilterBean.getSize() >= 0)
    // return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
    // return messageService.getAllMessages();
    // }

    // same methods with different producers (depends on Accept Header from client)
    // same stuff can be done for consumers (depends on Content-Type header from client)

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXMLMessages(@BeanParam MessageFilterBean messageFilterBean) {
	System.out.println("Clients wants XML");
	if (messageFilterBean.getYear() != null)
	    return messageService.getAllMessagesForYear(messageFilterBean.getYear());
	if (messageFilterBean.getStart() != null && messageFilterBean.getSize() != null && messageFilterBean
		.getStart() >= 0 && messageFilterBean.getSize() >= 0)
	    return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
	return messageService.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilterBean messageFilterBean) {
	System.out.println("Clients wants JSON");
	if (messageFilterBean.getYear() != null)
	    return messageService.getAllMessagesForYear(messageFilterBean.getYear());
	if (messageFilterBean.getStart() != null && messageFilterBean.getSize() != null && messageFilterBean
		.getStart() >= 0 && messageFilterBean.getSize() >= 0)
	    return messageService.getAllMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
	return messageService.getAllMessages();
    }

    // adhering to HATEOAS idea of adding links to connected resources - should be done in all methods
    @GET
    @Path("/{messageId}")
    // @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
	Message message = messageService.getMessage(messageId);
	message.addLinkIfAbsent(getUrlForSelf(uriInfo, message), "self");
	message.addLinkIfAbsent(getUrlForProfile(uriInfo, message), "profile");
	message.addLinkIfAbsent(getUrlForComments(uriInfo, message), "comments");
	return message;
    }

    // building resource url
    private String getUrlForSelf(UriInfo uriInfo, Message message) {
	String url = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(String.valueOf(message.getId()))
		.build()
		.toString();
	return url;
    }

    private String getUrlForProfile(UriInfo uriInfo, Message message) {
	String url = uriInfo.getBaseUriBuilder()
		.path(ProfileResource.class)
		.path(message.getAuthor())
		.build()
		.toString();
	return url;
    }

    private String getUrlForComments(UriInfo uriInfo, Message message) {
	String url = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		// path to a method
		.path(MessageResource.class, "goToCommentResource")
		// replacing {messageId} of a method to actual value
		.resolveTemplate("messageId", message.getId())
		.build()
		.toString();
	return url;
    }

    @POST
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Produces(MediaType.APPLICATION_JSON)
    // public Message addMessage(Message message) {
    // return messageService.addMessage(message);
    // }
    // way to control status codes and headers
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
	Message newMessage = messageService.addMessage(message);
	String newId = String.valueOf(newMessage.getId());
	URI resourceUri = uriInfo.getAbsolutePathBuilder()
		.path(newId)
		.build();
	// sets status code to 200 and returns resource URI in the location header - we can do it manually by accessing
	// header
	return Response./* status(Status.CREATED) */created(resourceUri)
		.entity(newMessage)
		.build();
    }

    @PUT
    @Path("/{messageId}")
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Produces(MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
	message.setId(messageId);
	return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
	messageService.removeMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource goToCommentResource() {
	return new CommentResource();
    }
}
