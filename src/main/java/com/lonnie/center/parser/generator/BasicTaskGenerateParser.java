package com.lonnie.center.parser.generator;

import java.util.ArrayList;
import java.util.List;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;
import com.lonnie.center.util.HttpConnectionUtil;

public abstract class BasicTaskGenerateParser {

	public List<CaptureTask> execute(TaskGenerateTrigger taskGenerateTrigger) throws Exception {
		String content;
		List<CaptureTask> results = new ArrayList<CaptureTask>();
		try {
			content = HttpConnectionUtil.getContentByTask(taskGenerateTrigger);
			results = parseResult(content, taskGenerateTrigger);
		} catch (Exception e) {
			throw e;
		}
		return results;

	}

	public abstract List<CaptureTask> parseResult(String content, TaskGenerateTrigger taskGenerateTrigger);
	
}
