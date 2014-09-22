package com.lonnie.center.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	public static Logger getLogger(String clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static void info(String clazz, String message) {
		//getLogger(clazz).info(message);
		System.out.println(clazz + "    " + message);
	}
	
	public static void error(String clazz, String message) {
		//getLogger(clazz).error(message);
		System.out.println(clazz + "    " + message);
	}
}
