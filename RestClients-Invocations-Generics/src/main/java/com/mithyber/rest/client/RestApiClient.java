package com.mithyber.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mithyber.messenger.model.Message;

public class RestApiClient {

    public static void main(String[] args) {
	// creating rest client - usually only one for all requests ?
	Client client = ClientBuilder.newClient();
	// url to make request
	// Response response = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/messages/1")
	// getting builder that can be used to configure request headers etc.
	// .request()
	// .request(MediaType.APPLICATION_JSON)
	// http method
	// .get();
	// unwrap object instance of concrete class
	// Message message = response.readEntity(Message.class);
	// receive object without getting response object

	// Message message = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/messages/2")
	// .request(MediaType.APPLICATION_JSON)
	// .get(Message.class);

	// usually you would extract baseTarget for the rest apis
	WebTarget baseTarget = client.target("http://localhost:8080/advanced-jaxrs-06/webapi/");
	// and then you would add path to concrete resources - in this case it's base target for all messages
	WebTarget messageTarget = baseTarget.path("messages");
	// generic target to single message
	WebTarget singleMessageTarget = messageTarget.path("{messageId}");

	Message message = singleMessageTarget.resolveTemplate("messageId", 1)
		.request(MediaType.APPLICATION_JSON)
		.get(Message.class);

	Message secondMessage = singleMessageTarget.resolveTemplate("messageId", 2)
		.request(MediaType.APPLICATION_JSON)
		.get(Message.class);

	// Creating new message with POST request
	Message newMessage = new Message(13, "War is peace", "BigBrother");
	Response postResponce = messageTarget.request()
		// sending message as json
		.post(Entity.json(newMessage));
	// u can check different stuff with response object
	if (postResponce.getStatus() != 201)
	    System.out.println("Error!");
	Message createdMessage = postResponce.readEntity(Message.class);

	System.out.println(message.getMessage());
	System.out.println(secondMessage.getMessage());
	System.out.println(createdMessage.getMessage());
    }

}
