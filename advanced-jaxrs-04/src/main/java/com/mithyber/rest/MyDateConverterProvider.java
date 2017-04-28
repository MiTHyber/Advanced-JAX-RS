package com.mithyber.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

public class MyDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
	if (rawType == MyDate.class)
	    return new ParamConverter<T>() {
		@Override
		public T fromString(String value) {
		    return null;
		}

		@Override
		public String toString(T value) {
		    // we're not doing it there, because we only get MyDate, but never send it to the clients
		    return null;
		}
	    };
	return null;
    }

}
