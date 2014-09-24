package com.lonnie.center.capture.excutor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.lonnie.center.parser.generator.BasicTaskGenerateParser;
import com.lonnie.center.parser.generator.BoleTaskGenerateParser;
import com.lonnie.center.parser.generator.CSDNBlogTaskGenerateParser;
import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;
import com.lonnie.center.task.service.TaskService;
import com.lonnie.center.util.LogUtil;

/**
 * Execute through quartz by interval
 * 
 * @author LULO2
 */
public class TaskGenerationExcutor {

	private TaskService taskService;
	private List<TaskGenerateTrigger> triggers;

	public void run() {
		try {
			LogUtil.info(this.getClass().getName(), "Start task generation..");
			
			List<CaptureTask> tasks = generateNewTask();
			LogUtil.info(this.getClass().getName(), "Task generation end..");
			
			storeNewTask(tasks);
			LogUtil.info(this.getClass().getName(), "Save new tasks end..");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(this.getClass().getName(), "Got exception when generate new task:" + e.getMessage());
		}
	}

	/**
	 * Store new task
	 * @param tasks to store
	 */
	private void storeNewTask(List<CaptureTask> tasks) {
		//TODO not open for not impl yet
		//getTaskService().generateNewTask(tasks);
	}

	/**
	 * Generate new task by task trigger
	 * @return Capture task new
	 * @throws Exception 
	 */
	private List<CaptureTask> generateNewTask() throws Exception {
		List<CaptureTask> tasks = new ArrayList<CaptureTask>();
		if (CollectionUtils.isEmpty(triggers)) {
			triggers = getTaskService().loadTaskTriggers();
		}
		for (TaskGenerateTrigger taskGenerateTrigger : triggers) {
			BasicTaskGenerateParser parser = getParserByTaskTrigger(taskGenerateTrigger);
			if (null != parser) {
				tasks.addAll(parser.execute(taskGenerateTrigger));
			}
		}
		return tasks;
	}

	/**
	 * Get result parser by task
	 * @param taskGenerateTrigger
	 * @return parser for parse html
	 */
	private BasicTaskGenerateParser getParserByTaskTrigger(TaskGenerateTrigger taskGenerateTrigger) {
		if (StringUtils.equals(taskGenerateTrigger.getParser(), "BoleTaskGenerateParser")) {
			return new BoleTaskGenerateParser();
		} else if (StringUtils.equals(taskGenerateTrigger.getParser(), "CSDNBlogTaskGenerateParser")) {
			return new CSDNBlogTaskGenerateParser();
		}
		return null;
	}

	/**
	 * @return the taskService
	 */
	public TaskService getTaskService() {
		return taskService;
	}

	/**
	 * @param taskService
	 *            the taskService to set
	 */
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	/**
	 * Just use for testing
	 * @param triggers the triggers to set
	 */
	public void setTriggers(List<TaskGenerateTrigger> triggers) {
		this.triggers = triggers;
	}
}
