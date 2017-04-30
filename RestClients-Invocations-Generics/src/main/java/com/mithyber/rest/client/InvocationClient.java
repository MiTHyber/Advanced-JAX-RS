package com.mithyber.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Invocation is kinda prepared request that u can invoke whenever u want
public class InvocationClient {
    public static void main(String[] args) {
	InvocationClient client = new InvocationClient();
	Invocation invocation = client.prepareRequestForMessagesByYear(2017);
	Response response = invocation.invoke();
	System.out.println(response.getStatus());
    }

    public Invocation prepareRequestForMessagesByYear(int year) {
	Client client = ClientBuilder.newClient();
	return client.target("http://localhost:8080/advanced-jaxrs-06/webapi")
		.path("messages")
		.queryParam("year", year)
		.request(MediaType.APPLICATION_JSON)
		.buildGet();
    }
}
