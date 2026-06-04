package com.haisclinic.haisclinic_app_backend.Exceptions;

public class AlreadyExistsException extends RuntimeException {

	public AlreadyExistsException(String message) {
		super(message);
	}
}
