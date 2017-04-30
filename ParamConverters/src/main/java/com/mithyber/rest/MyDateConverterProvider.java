package com.mithyber.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
	if (rawType == MyDate.class)
	    return new ParamConverter<T>() {
		@Override
		public T fromString(String value) {
		    LocalDateTime localDateTime = LocalDateTime.now();
		    if ("tomorrow".equals(value))
			localDateTime = localDateTime.plusDays(1);
		    else if ("yesterday".equals(value))
			localDateTime = localDateTime.minusDays(1);
		    MyDate myDate = new MyDate();
		    myDate.setDay(localDateTime.getDayOfMonth());
		    myDate.setMonth(localDateTime.getMonthValue());
		    myDate.setYear(localDateTime.getYear());
		    return rawType.cast(myDate);
		}

		@Override
		public String toString(T value) {
		    if (value == null)
			return null;
		    return value.toString();
		}
	    };
	return null;
    }

}
