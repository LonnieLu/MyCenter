package com.lonnie.center.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.lonnie.center.exception.HttpConnectionException;
import com.lonnie.center.task.TaskBasic;

public class HttpConnectionUtil {
	
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	
	/**
	 * Get html content through task
	 * @param task capture task
	 * @return html content
	 * @throws HttpConnectionException 
	 */
	public static String getContentByTask(TaskBasic task) throws HttpConnectionException {
		return getContentByTask(task.getUrl(), task.getHttpMethod());
	}
	
	/**
	 * Get html content through url and method
	 * @param urlStr the url going to capture
	 * @param method the method use to get result(POST/GET)
	 * @return html content
	 * @throws HttpConnectionException 
	 */
	public static String getContentByTask(String urlStr, String method) throws HttpConnectionException {
		HttpURLConnection http = null;
		try {
			URL url = new URL(urlStr);
			http = (HttpURLConnection) url.openConnection();
			buildHttpConnectionParam(method, http);
			
			return buildContentByStream(http.getInputStream(), "UTF-8");
		} catch (IOException ex) {
			//For some website may return 500(Bole Blog)
			//Try again by using error stream
			try {
				return buildContentByStream(http.getErrorStream(), "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				throw new HttpConnectionException("Failed to get result by url :" + ex.getMessage());
			}
		} catch (Exception e) {
			throw new HttpConnectionException("Failed to get result by url :" + e.getMessage());
		} finally {
			if (http != null) {
				http.disconnect();
			}
		}
	}

	/**
	 * Build content string base on input stream and charset 
	 * @param inputStream
	 * @param charset 
	 * @return html content
	 * @throws IOException 
	 */
	private static String buildContentByStream(InputStream inputStream, String charset) throws IOException {
		BufferedReader l_reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		l_reader = new BufferedReader(new InputStreamReader(inputStream, charset));
		String sCurrentLine = null;
		while ((sCurrentLine = l_reader.readLine()) != null) {
			resultBuffer.append(sCurrentLine);
			resultBuffer.append("\r\n");
		}
		l_reader.close();
		return resultBuffer.toString();
	}

	private static void buildHttpConnectionParam(String method, HttpURLConnection http) throws ProtocolException {
		http.setConnectTimeout(CaptureConfig.getTimeout());
		http.setReadTimeout(CaptureConfig.getTimeout());
		http.setRequestMethod(StringUtils.isEmpty(method) ? "GET" : method);
		http.setDoOutput(true);
		http.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
	}

}
