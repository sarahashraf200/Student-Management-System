package com.luv2code.springboot.cruddemo.exceptions.custom;

public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException(Class<?> className, Long id) {
		super(String.format("The record of class %s with id %d is not existing", className.getSimpleName(), id));
	}
}