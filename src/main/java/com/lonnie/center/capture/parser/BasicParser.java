package com.lonnie.center.capture.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lonnie.center.capture.WebResult;
import com.lonnie.center.capture.task.CaptureTask;
import com.lonnie.center.exception.HttpConnectionException;
import com.lonnie.center.exception.UnableToParseResultException;
import com.lonnie.center.util.CaptureConfig;

public abstract class BasicParser {

	/**
	 * Execute task
	 * get url content and then parse it as result object
	 * If no exception happen, update the task as Success
	 * Else update it as Failed
	 * @param task
	 * @return result object
	 */
	public List<WebResult> execute(CaptureTask task) {
		String content;
		List<WebResult> results = new ArrayList<WebResult>();
		try {
			content = getContentByTask(task);
			results = parseResult(content, task);
			updateTaskStatus(task, "", "Success");
		} catch (HttpConnectionException e) {
			updateTaskStatus(task, e.getExceptionDesc(), "Failed");
		} catch (UnableToParseResultException e) {
			updateTaskStatus(task, e.getExceptionDesc(), "Failed");
		} catch (Exception e) {
			updateTaskStatus(task, e.getMessage(), "Failed");
		}
		return results;
	}

	/**
	 * Get html content through url
	 * @param task
	 * @return html content
	 * @throws HttpConnectionException 
	 */
	public String getContentByTask(CaptureTask task) throws HttpConnectionException {
		BufferedReader l_reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		HttpURLConnection http = null;
		try {
			URL url = new URL(task.getUrl());
			
			http = (HttpURLConnection) url.openConnection();
			
			http.setConnectTimeout(CaptureConfig.getTimeout());
			http.setReadTimeout(CaptureConfig.getTimeout());
			http.setRequestMethod(StringUtils.isEmpty(task.getHttpMethod()) ? "GET" : task.getHttpMethod());
			http.setDoOutput(true);
			http.setRequestProperty("User-agent","Mozilla/5.0");
			
			l_reader = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
			
			String sCurrentLine = null;
			while ((sCurrentLine = l_reader.readLine()) != null) {
				resultBuffer.append(sCurrentLine);
				resultBuffer.append("\r\n");
			}
			return resultBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new HttpConnectionException("Failed to get result by url :" + ex.getMessage());
		} finally {
			if (null != l_reader){
				try {
					l_reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (http != null) {
				http.disconnect();
			}
		}
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
			
			System.out.println(result.toString());
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

	/**
	 * Update task status
	 * @param task the task to update
	 * @param exceptionDesc the exception description, only got value when failed
	 * @param status Failed/Success
	 */
	private void updateTaskStatus(CaptureTask task, String exceptionDesc,
			String status) {
		// TODO Auto-generated method stub
	}
}
