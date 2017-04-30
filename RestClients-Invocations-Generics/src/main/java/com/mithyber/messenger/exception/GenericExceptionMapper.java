package com.mithyber.messenger.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.mithyber.messenger.model.ErrorMessage;

//@Provider
// it catches all exceptions that don't have more specific mappers
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
	ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "there is no URI to documentation");
	return Response.status(Status.INTERNAL_SERVER_ERROR)
		.type(MediaType.APPLICATION_JSON)
		.entity(errorMessage)
		.build();
    }

}
