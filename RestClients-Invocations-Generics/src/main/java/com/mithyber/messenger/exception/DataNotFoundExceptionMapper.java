package com.mithyber.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mithyber.messenger.model.ErrorMessage;

// it catches this kind of exceptions and give s proper responce
// @Provider registers mapper to jax-rs
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException exception) {
	ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "there is no URI to documentation");
	return Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();
    }

}
