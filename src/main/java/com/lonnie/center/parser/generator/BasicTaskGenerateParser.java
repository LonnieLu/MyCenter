package com.lonnie.center.parser.generator;

import java.util.ArrayList;
import java.util.List;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;
import com.lonnie.center.util.HttpConnectionUtil;

public abstract class BasicTaskGenerateParser {

	/**
	 * Build capture task per capture content
	 * @param taskGenerateTrigger
	 * @return List<CaptureTask> capture task list to return
	 * @throws Exception
	 */
	public List<CaptureTask> execute(TaskGenerateTrigger taskGenerateTrigger) throws Exception {
		List<CaptureTask> results = new ArrayList<CaptureTask>();
		try {
			String content = HttpConnectionUtil.getContentByTask(taskGenerateTrigger);
			results = parseResult(content, taskGenerateTrigger);
			updateCaptureTaskTrigger(taskGenerateTrigger);
		} catch (Exception e) {
			throw e;
		}
		return results;

	}

	/**
	 * Parse result from return html string
	 * @param content html result from url
	 * @param taskGenerateTrigger
	 * @return List<CaptureTask> capture task list to return
	 */
	public abstract List<CaptureTask> parseResult(String content, TaskGenerateTrigger taskGenerateTrigger);
	
	/**
	 * Update capture task trigger
	 * e.g. Capture from last page, update to previous page to capture old data
	 * @param taskGenerateTrigger
	 */
	public abstract void updateCaptureTaskTrigger(TaskGenerateTrigger taskGenerateTrigger);
	
	/**
	 * Parser use for task
	 * use for build CaptureTask
	 * @return parser class name
	 */
	public abstract String getParser();
	
	/**
	 * Http request method use to get content for capture task
	 * @return http request method string GET/POST
	 */
	public abstract String getHttpMethod();
	
}
