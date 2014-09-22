package com.lonnie.center.capture.task;

public class CaptureTask {

	private String parser;
	private String url;
	private String httpMethod;
	
	public CaptureTask() {
	}

	public CaptureTask(String parser, String url, String httpMethod) {
		this.parser = parser;
		this.url = url;
		this.httpMethod = httpMethod;
	}

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
}
