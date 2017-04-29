package com.mithyber.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mithyber.messenger.model.Message;

public class GenericClient {

    public static void main(String[] args) {
	Client client = ClientBuilder.newClient();
	List<Message> messages = client.target("http://localhost:8080/advanced-jaxrs-06/webapi")
		.path("messages")
		.queryParam("year", 2017)
		.request(MediaType.APPLICATION_JSON)
		// it's a way to get an instance of a generic type
		.get(new GenericType<List<Message>>() {
		});
	System.out.println(messages);
    }

}
