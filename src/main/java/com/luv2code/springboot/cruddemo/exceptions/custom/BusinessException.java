package com.luv2code.springboot.cruddemo.exceptions.custom;

public class BusinessException extends RuntimeException {

	private Object[] params = null;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Object[] params) {
		super(message);
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
}