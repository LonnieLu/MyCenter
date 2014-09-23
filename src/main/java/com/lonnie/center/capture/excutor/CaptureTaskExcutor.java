package com.lonnie.center.capture.excutor;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lonnie.center.capture.WebResult;
import com.lonnie.center.capture.parser.BasicParser;
import com.lonnie.center.capture.parser.BoleBlogParser;
import com.lonnie.center.capture.parser.CSDNBlogParser;
import com.lonnie.center.capture.task.pool.CaptureTaskPool;
import com.lonnie.center.exception.HttpConnectionException;
import com.lonnie.center.exception.UnableToParseResultException;
import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.service.TaskService;
import com.lonnie.center.util.CaptureConfig;
import com.lonnie.center.util.LogUtil;

public class CaptureTaskExcutor extends Thread {
	
	private TaskService taskService;
	
	@Override
	public void run() {
		while (true) {
			CaptureTask task = CaptureTaskPool.getCaptureTask();
			try {
				task = CaptureTaskPool.getCaptureTask();
				if (null != task) {
					LogUtil.info("CaptureTaskExcutor", "Task [" + task.getUrl() + "] start..");
					BasicParser parser = getParserByTask(task);
					if (null == parser) {
						LogUtil.error("CaptureTaskExcutor", "No class found for " + task.getParser());
					} else {
						List<WebResult> results = parser.execute(task);
						LogUtil.info("CaptureTaskExcutor", "Task [" + task.getUrl() + "] capture, got [" + results.size() + "]result..");
						updateTaskStatus(task, "", "Success");
						LogUtil.info("CaptureTaskExcutor", "Update task [" + task.getUrl() + "] status completed..");
						storeCaptureResult(results);
						LogUtil.info("CaptureTaskExcutor", "Task [" + task.getUrl() + "] store completed..");
					}
				}
			} catch (HttpConnectionException e) {
				updateTaskStatus(task, e.getExceptionDesc(), "Failed");
			} catch (UnableToParseResultException e) {
				updateTaskStatus(task, e.getExceptionDesc(), "Failed");
			} catch (Exception e) {
				updateTaskStatus(task, e.getMessage(), "Failed");
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
		} else if (StringUtils.equals(task.getParser(), "CSDNBlogParser")) {
			return new CSDNBlogParser();
		}
		return null;
	}
	
	/**
	 * Update task status
	 * @param task the task to update
	 * @param exceptionDesc the exception description, only got value when failed
	 * @param status Failed/Success
	 */
	private void updateTaskStatus(CaptureTask task, String exceptionDesc,
			String status) {
		getTaskService().updateTaskStatus(task, status, exceptionDesc);
	}
	
	/**
	 * @return the taskService
	 */
	public TaskService getTaskService() {
		return taskService;
	}

	/**
	 * @param taskService the taskService to set
	 */
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
