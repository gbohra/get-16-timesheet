package com.timesheet.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.Task;
import com.timesheet.service.CurrentUserService;
import com.timesheet.service.TaskService;
import com.timesheet.vo.TaskVO;

@Controller
@RequestMapping(value="/api/v1/tasks")
public class TaskController {
	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@Autowired
	private TaskService taskService;
	
	/**
	 * This create a new task 
	 * @param task - take parameter task and save
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public TaskVO createTask(@RequestBody TaskVO taskVO){
		return taskService.createTask(taskVO);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public TaskVO updateTask(@RequestBody TaskVO taskVO){
		return taskService.updateTask(taskVO);
	}
	
	
	
	
	/**
	 * get all users task according to id
	 * @param userId - users id 
	 * @return
	 */
	
	@RequestMapping(value="/me",method=RequestMethod.GET)
	@ResponseBody
	public List<Task> getTaskMe() {
		return taskService.getTaskMe();
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param dates - date as a string  
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/me/{date}",method=RequestMethod.GET)
	@ResponseBody
	public List getTaskByDate(@PathVariable("date") Long date){
		return taskService.getTaskByDate(date);
	}
}
