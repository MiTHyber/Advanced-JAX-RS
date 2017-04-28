package com.mithyber.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//@Singleton
@Path("{pathParam}/test")
public class MyResource {

    // u can use all params annotatations not only with arguments, but fields as well
    // it's not possible in case of singleton, because objects get created during app startup
    @PathParam("pathParam")
    private String pathParamExample;
    @QueryParam("query")
    private String queryParamExample;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
	return "It works! Path param = " + pathParamExample + ", query param = " + queryParamExample;
    }
}
