package com.lonnie.center.task;

public class CaptureTask extends TaskBasic {
	
	private String title;
	
	public CaptureTask() {
	}
	
	public CaptureTask(String parser, String url, String httpMethod) {
		setParser(parser);
		setUrl(url);
		setHttpMethod(httpMethod);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
