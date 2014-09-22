package com.lonnie.center.capture.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
	public abstract List<WebResult> parseResult(String content, CaptureTask task) throws UnableToParseResultException;
	
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
