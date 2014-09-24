package com.lonnie.capture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lonnie.center.capture.excutor.TaskGenerationExcutor;
import com.lonnie.center.task.TaskGenerateTrigger;

public class TaskGenerationTest {
	
	TaskGenerationExcutor trigger = new TaskGenerationExcutor();
	
	@Before
	public void init() {
		List<TaskGenerateTrigger> tasks = new ArrayList<TaskGenerateTrigger>();
		TaskGenerateTrigger t1 = new TaskGenerateTrigger("BoleTaskGenerateParser","http://blog.jobbole.com/all-posts/page/323/", "GET");
		tasks.add(t1);
		trigger.setTriggers(tasks);
	}

	@Test
	public void testCapture() {
		trigger.run();
	}
}
