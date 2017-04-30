package com.mithyber.rest;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "secured";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
	// checking auth only for APIs with "secured" in the path
	if (requestContext.getUriInfo()
		.getPath()
		.contains(SECURED_URL_PREFIX)) {
	    List<String> authHeaders = requestContext.getHeaders()
		    .get(AUTHORIZATION_HEADER);
	    // it's kinda silly, but point is - request should have this header (1)
	    if (authHeaders != null && authHeaders.size() > 0) {
		String firstAuthHeader = authHeaders.get(0);
		String authToken = firstAuthHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		byte[] decodedBytes = Base64.getDecoder()
			.decode(authToken);
		String decodedString = new String(decodedBytes, "UTF-8");
		StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		if ("saya".equals(username) && "goddess".equals(password))
		    // jaxrs can proceed with a request
		    return;
	    }
	    Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED)
		    .entity("User cannot access the resource")
		    .build();
	    // aborting request
	    requestContext.abortWith(unauthorizedStatus);
	}
    }

}
