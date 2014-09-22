package com.lonnie.center.exception;

public class HttpConnectionException extends Exception {

	private static final long serialVersionUID = 2586409792249766467L;
	private String exceptionDesc;

	public HttpConnectionException() {
		super();
	}

	public HttpConnectionException(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}
}
