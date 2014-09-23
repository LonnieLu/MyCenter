package com.lonnie.center.task.service;

import java.util.List;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;

public interface TaskService {

	public void generateNewTask(List<CaptureTask> tasks);
	
	public void updateTaskStatus(CaptureTask task, String status, String description);
	
	public List<CaptureTask> loadTaskToTaskPool(int poolSize);
	
	public List<TaskGenerateTrigger> loadTaskTriggers();
}
