package com.mithyber.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// way to bootstrap rest without web.xml
// @ApplicationPath maps web service to some path
// we don't need to provide packages where jaxrs would search for resources - it just scans all classpath,
// but we can implement getClasses methods to choose exact set of resources to publish
// if getClasses and getSingletons returns empty set then default scanning all packages works
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
