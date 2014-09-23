package com.lonnie.center.task;

public class CaptureTask extends TaskBasic {

	public CaptureTask(String parser, String url, String httpMethod) {
		setParser(parser);
		setUrl(url);
		setHttpMethod(httpMethod);
	}
}
