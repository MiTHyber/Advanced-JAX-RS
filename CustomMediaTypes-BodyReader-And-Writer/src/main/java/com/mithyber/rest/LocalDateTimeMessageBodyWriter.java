package com.mithyber.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
// writer responsible for plain text
@Produces(MediaType.TEXT_PLAIN)
public class LocalDateTimeMessageBodyWriter implements MessageBodyWriter<LocalDateTime> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	// it works for childs too
	return LocalDateTime.class.isAssignableFrom(type);
    }

    // deprecated - preferred return value is -1
    @Override
    public long getSize(LocalDateTime t, Class<?> type, Type genericType, Annotation[] annotations,
	    MediaType mediaType) {
	return -1;
    }

    @Override
    public void writeTo(LocalDateTime t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
	    MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
	    WebApplicationException {
	entityStream.write(t.toString()
		.getBytes());
    }

}
