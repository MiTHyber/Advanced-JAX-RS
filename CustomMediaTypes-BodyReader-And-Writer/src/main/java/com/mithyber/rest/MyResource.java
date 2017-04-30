package com.mithyber.rest;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class MyResource {

    @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // MediaType is just a string - u can call it whatever you want - you just need messagebodywriter and
    // messagebodyreader for this type
    @Produces(value = { MediaType.TEXT_PLAIN, "text/shortdate" })
    public LocalDateTime testMethod() {
	return LocalDateTime.now();
    }
}
