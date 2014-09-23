package com.lonnie.center.parser.capture;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lonnie.center.capture.WebResult;
import com.lonnie.center.exception.UnableToParseResultException;
import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.util.HttpConnectionUtil;

public abstract class BasicParser {
	
	/**
	 * Execute task
	 * get url content and then parse it as result object
	 * If no exception happen, update the task as Success
	 * Else update it as Failed
	 * @param task
	 * @return result object
	 * @throws Exception 
	 */
	public List<WebResult> execute(CaptureTask task) throws Exception {
		String content;
		List<WebResult> results = new ArrayList<WebResult>();
		try {
			content = HttpConnectionUtil.getContentByTask(task);
			results = parseResult(content, task);
		} catch (Exception e) {
			throw e;
		}
		return results;
	}

	/**
	 * Parse html and build result base on the content
	 * @param content
	 * @param task
	 * @return result object
	 */
	public List<WebResult> parseResult(String content, CaptureTask task) throws UnableToParseResultException {
		List<WebResult> webResults = new ArrayList<WebResult>();
		Document document = Jsoup.parse(content);
		if (isResultParseable(document)) {
			//Build result object
			WebResult result = new WebResult();
			result.setTitle(parseTitle(document));
			result.setCategories(parseCategories(document));
			result.setTags(parseTags(document));
			result.setContent(parseContents(document));
			result.setUrl(task.getUrl());
			result.setParser(task.getParser());
			webResults.add(result);
		} else {
			throw new UnableToParseResultException("Can't parser result for task [" + task.getUrl() + "]!!");
		}
		return webResults;
	}
	
	/**
	 * Check whether html result can parse or not
	 * @param document
	 * @return true/false
	 */
	public abstract boolean isResultParseable(Document document);
	
	/**
	 * Get title from html result
	 * @param document
	 * @return title
	 */
	public abstract String parseTitle(Document document);
	
	/**
	 * Get content from html result
	 * @param document
	 * @return content
	 */
	public abstract String parseContents(Document document);
	
	/**
	 * Get categories from html result
	 * @param document
	 * @return categories in list
	 */
	public abstract List<String> parseCategories(Document document);
	
	/**
	 * Get tags from html result
	 * @param document
	 * @return tags in list
	 */
	public abstract List<String> parseTags(Document document);

}
