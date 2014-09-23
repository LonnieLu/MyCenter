package com.lonnie.center.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.lonnie.center.exception.HttpConnectionException;
import com.lonnie.center.task.TaskBasic;

public class HttpConnectionUtil {
	
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
		BufferedReader l_reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		HttpURLConnection http = null;
		try {
			URL url = new URL(urlStr);
			
			http = (HttpURLConnection) url.openConnection();
			buildHttpConnectionParam(method, http);
			
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

	private static void buildHttpConnectionParam(String method, HttpURLConnection http) throws ProtocolException {
		http.setConnectTimeout(CaptureConfig.getTimeout());
		http.setReadTimeout(CaptureConfig.getTimeout());
		http.setRequestMethod(StringUtils.isEmpty(method) ? "GET" : method);
		http.setDoOutput(true);
		http.setRequestProperty("User-agent","Mozilla/5.0");
	}

}
