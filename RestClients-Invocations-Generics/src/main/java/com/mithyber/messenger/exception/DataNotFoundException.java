package com.mithyber.messenger.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -463862538096713117L;

    public DataNotFoundException(String message) {
	super(message);
    }

}
