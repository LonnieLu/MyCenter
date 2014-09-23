package com.lonnie.center.task.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;
import com.lonnie.center.task.repository.TaskRepository;

public class TaskServiceImpl implements TaskService {
	
	private TaskRepository taskRepository;

	public void generateNewTask(List<CaptureTask> tasks) {
		Set<CaptureTask> taskSet = new HashSet<CaptureTask>();
		for (CaptureTask captureTask : taskSet) {
			if (!getTaskRepository().checkTaskExist(captureTask)) {
				taskSet.add(captureTask);
			}
		}
		getTaskRepository().generateNewTasks(new ArrayList<CaptureTask>(taskSet));
	}

	public void updateTaskStatus(CaptureTask task, String status,
			String description) {
		getTaskRepository().updateTaskStatus(task, status, description);
	}
	
	/**
	 * @return the taskRepository
	 */
	public TaskRepository getTaskRepository() {
		return taskRepository;
	}

	/**
	 * @param taskRepository the taskRepository to set
	 */
	public void setTaskRepository(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<CaptureTask> loadTaskToTaskPool(int poolSize) {
		return getTaskRepository().loadTaskToTaskPool(poolSize);
	}

	public List<TaskGenerateTrigger> loadTaskTriggers() {
		return getTaskRepository().loadTaskTriggers();
	}

}
