package com.lonnie.center.exception;

public class UnableToParseResultException extends Exception {

	private static final long serialVersionUID = 2586409792249766467L;
	private String exceptionDesc;

	public UnableToParseResultException() {
		super();
	}

	public UnableToParseResultException(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}
}
