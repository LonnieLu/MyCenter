package com.lonnie.center.parser.generator;

import java.util.ArrayList;
import java.util.List;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;


public class BoleTaskGenerateParser extends BasicTaskGenerateParser {

	@Override
	public List<CaptureTask> parseResult(String content, TaskGenerateTrigger taskGenerateTrigger) {
		System.out.println(content);
		return new ArrayList<CaptureTask>();
	}

}
