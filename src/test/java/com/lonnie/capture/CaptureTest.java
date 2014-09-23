package com.lonnie.capture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lonnie.center.capture.excutor.CaptureTaskExcutor;
import com.lonnie.center.capture.task.pool.CaptureTaskPool;
import com.lonnie.center.task.CaptureTask;

public class CaptureTest {
	
	@Before
	public void init() {
		List<CaptureTask> tasks = new ArrayList<CaptureTask>();
		CaptureTask t1 = new CaptureTask("BoleBlogParser","http://blog.jobbole.com/74107/", "GET");
		tasks.add(t1);

		CaptureTask t5 = new CaptureTask("CSDNBlogParser","http://blog.csdn.net/w337198302/article/details/39430647", "GET");
		tasks.add(t5);
		
		/*
		CaptureTask t2 = new CaptureTask("BoleBlogParser","http://blog.jobbole.com/76983/", "GET");
		CaptureTask t3 = new CaptureTask("BoleBlogParser","http://blog.jobbole.com/76714/", "GET");
		CaptureTask t4 = new CaptureTask("BoleBlogParser","http://blog.jobbole.com/39602/", "GET");
		tasks.add(t2);
		tasks.add(t3);
		tasks.add(t4);
		*/
		CaptureTaskPool.setCaptureTasks(tasks);
	}

	@Test
	public void testCapture() {
		CaptureTaskExcutor t1 = new CaptureTaskExcutor();
		t1.run();
	}
}
