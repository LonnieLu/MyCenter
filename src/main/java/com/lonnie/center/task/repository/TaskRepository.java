package com.lonnie.center.task.repository;

import java.util.List;

import com.lonnie.center.task.CaptureTask;

public interface TaskRepository {

	public void generateNewTasks(List<CaptureTask> tasks);
	
	public void updateTaskStatus(CaptureTask task, String status, String description);
	
	public boolean checkTaskExist(CaptureTask task);
	
}
