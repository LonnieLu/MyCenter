package com.lonnie.center.task;

public class TaskGenerateTrigger extends TaskBasic {

	public TaskGenerateTrigger(String parser, String url, String httpMethod) {
		setParser(parser);
		setUrl(url);
		setHttpMethod(httpMethod);
	}
}
