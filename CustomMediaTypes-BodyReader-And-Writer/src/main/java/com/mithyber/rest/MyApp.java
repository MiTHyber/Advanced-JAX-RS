package com.mithyber.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("webapi")
public class MyApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
	return null;
    }

    @Override
    public Set<Object> getSingletons() {
	return null;
    }

}
