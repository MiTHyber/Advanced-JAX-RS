package com.mithyber.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

    // @MatrixParam separated by c; instead of ?
    // @HeaderParam just as it says
    // @CookieParam is kinda obvious, don't you think?
    // @FormParam, @QueryParam and @PathParam is obvious too
    @GET
    @Path("/annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
	    @HeaderParam("param") String headerParam, @CookieParam("param") String cookieParam) {
	return "Matrix param = " + matrixParam + "; Header param = " + headerParam + "; Cookie param = " + cookieParam;
    }

    // @Context is handy when u don't know exact names of params and want to cycle through all of them
    @GET
    @Path("/context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
	return "Path = " + uriInfo.getAbsolutePath() + "; Cookie = " + httpHeaders.getCookies();
    }
}
