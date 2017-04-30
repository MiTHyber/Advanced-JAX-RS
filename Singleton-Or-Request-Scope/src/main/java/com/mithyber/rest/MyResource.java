package com.mithyber.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
// resources are Request Scope by default (new object on every request) - but with this annotation there is one object
// for all requests
@Singleton
public class MyResource {

    private int count;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
	return "It works! This method was called " + ++count + " time(s)";
    }
}
