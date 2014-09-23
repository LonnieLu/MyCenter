package com.lonnie.center.task.repository;

import java.util.List;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;

public class TaskRepositoryImple implements TaskRepository {

	public void generateNewTasks(List<CaptureTask> tasks) {
		// TODO Auto-generated method stub
		
	}

	public void updateTaskStatus(CaptureTask task, String status, String description) {
		// TODO Auto-generated method stub
		
	}

	public boolean checkTaskExist(CaptureTask task) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<CaptureTask> loadTaskToTaskPool(int poolSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TaskGenerateTrigger> loadTaskTriggers() {
		// TODO Auto-generated method stub
		return null;
	}


}
