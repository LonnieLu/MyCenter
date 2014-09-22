package com.lonnie.center.util;

import java.util.ResourceBundle;

public class CaptureConfig {

	private static ResourceBundle bundle;
	
	public static void init() {
		if (null == bundle) {
			bundle = ResourceBundle.getBundle("capture-config");
		}
	}
	
	public static int getCaptureInterval(){
		init();
		return Integer.parseInt(bundle.getString("CAPTURE_INTERVAL")) * 1000;
	}
	
	public static int getCaptureToolSize(){
		init();
		return Integer.parseInt(bundle.getString("CAPTURE_TASK_POOL_SIZE"));
	}

	public static int getTimeout() {
		init();
		return Integer.parseInt(bundle.getString("CAPTURE_TIME_OUT")) * 1000;
	}
}
