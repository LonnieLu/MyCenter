package com.lonnie.center.capture.excutor;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lonnie.center.capture.WebResult;
import com.lonnie.center.capture.parser.BasicParser;
import com.lonnie.center.capture.parser.BoleBlogParser;
import com.lonnie.center.capture.parser.CSDNBlogParser;
import com.lonnie.center.capture.task.CaptureTask;
import com.lonnie.center.capture.task.pool.CaptureTaskPool;
import com.lonnie.center.util.CaptureConfig;
import com.lonnie.center.util.LogUtil;

public class CaptureTaskExcutor extends Thread {
	
	@Override
	public void run() {
		while (true) {
			try {
				CaptureTask task = CaptureTaskPool.getCaptureTask();
				if (null != task) {
					LogUtil.info("CaptureTaskExcutor", "Task [" + task.getUrl() + "] start..");
					BasicParser parser = getParserByTask(task);
					if (null == parser) {
						LogUtil.error("CaptureTaskExcutor", "No class found for " + task.getParser());
					} else {
						List<WebResult> results = parser.execute(task);
						LogUtil.info("CaptureTaskExcutor", "Task [" + task.getUrl() + "] capture, got [" + results.size() + "]result..");
						storeCaptureResult(results);
						LogUtil.info("CaptureTaskExcutor", "Task [" + task.getUrl() + "] store completed..");
					}
				}
			} catch (Exception e) {
				LogUtil.error("CaptureTaskExcutor", "Got exception when excute task: " + e.getMessage());
			} finally {
				try {
					LogUtil.info("CaptureTaskExcutor", "Sleep:" + CaptureConfig.getCaptureInterval());
					Thread.sleep(CaptureConfig.getCaptureInterval());
				} catch (InterruptedException e) {
					LogUtil.error("CaptureTaskExcutor", e.getMessage());
				}
			}
		}
	}

	private void storeCaptureResult(List<WebResult> results) {
		// TODO Store result to DB for mahout suggestion
		// TODO Store result to lucene for search
	}

	private BasicParser getParserByTask(CaptureTask task) {
		if (StringUtils.equals(task.getParser(), "BoleBlogParser")) {
			return new BoleBlogParser();
		} else if (StringUtils.equals(task.getParser(), "CSDNParser")) {
			return new CSDNBlogParser();
		}
		return null;
	}

}
