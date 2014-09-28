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
		/*TaskGenerateTrigger t1 = new TaskGenerateTrigger("BoleTaskGenerateParser","http://blog.jobbole.com/all-posts/", "GET");
		tasks.add(t1);*/
		
		/*TaskGenerateTrigger t2 = new TaskGenerateTrigger("CSDNBlogTaskGenerateParser","http://blog.csdn.net/?page=110", "GET");
		tasks.add(t2);*/
		
		/*TaskGenerateTrigger t3 = new TaskGenerateTrigger("ImportNewTaskGenerateParser","http://www.importnew.com/page/32", "GET");
		tasks.add(t3);*/
		
		TaskGenerateTrigger t4 = new TaskGenerateTrigger("IFeveTaskGenerateParser","http://ifeve.com/page/31/", "GET");
		tasks.add(t4);
		
		/*TaskGenerateTrigger t5 = new TaskGenerateTrigger("CSDNSDTaskGenerateParser","http://sd.csdn.net/sd/488", "GET");
		tasks.add(t5);*/
		
		/*TaskGenerateTrigger t6 = new TaskGenerateTrigger("CSDNNewsTaskGenerateParser","http://news.csdn.net/news/1841", "GET");
		tasks.add(t6);*/
		
		trigger.setTriggers(tasks);
	}

	@Test
	public void testCapture() {
		trigger.run();
	}
}
