package com.lonnie.center.capture;

import java.util.List;

public class WebResult {

	private String title;
	private String url;
	private String content;
	private String parser;
	private List<String> categories;

	public String getParser() {
		return parser;
	}

	public void setParser(String parser) {
		this.parser = parser;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return "Title:\n\n\n" + getTitle() + "\n\n\nContenet\n\n\n" + getContent();
	}
}
