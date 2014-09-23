package com.lonnie.center.task;

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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.getUrl().hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CaptureTask) {
			CaptureTask compare = (CaptureTask) obj;
			return compare.getUrl().equals(this.getUrl());
		}
		return false;
	}
}
