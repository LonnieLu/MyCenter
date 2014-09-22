package com.lonnie.center.capture.task.pool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.lonnie.center.capture.task.CaptureTask;
import com.lonnie.center.util.LogUtil;

public class CaptureTaskPool {

	private static List<CaptureTask> captureTasks;
	
	public static synchronized CaptureTask getCaptureTask() {
		if (CollectionUtils.isEmpty(captureTasks)) {
			captureTasks = getTaskForCapture();
		}
		if (CollectionUtils.isEmpty(captureTasks)) {
			LogUtil.info("CaptureTaskPool","No new task generator.");
			return null;
		} else {
			CaptureTask task = captureTasks.remove(0);
			LogUtil.info("CaptureTaskPool","Task [" + task.getUrl() + "] going to excute...");
			LogUtil.info("CaptureTaskPool","Remained [" + captureTasks.size() + "] tasks in pool...");
			return task;
		}
	}

	private static List<CaptureTask> getTaskForCapture() {
		//TODO get task list generate by CaptureTaskGenerator
		return new ArrayList<CaptureTask>();
	}
	
	public static void setCaptureTasks(List<CaptureTask> captureTasksTest) {
		captureTasks = captureTasksTest;
	}
}
