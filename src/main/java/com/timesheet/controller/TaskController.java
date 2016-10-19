package com.timesheet.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.Task;
import com.timesheet.service.TaskService;
@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping(value="api/v1/task")
public class TaskController {
	@Autowired
	TaskService taskService;
	
	/**
	 * This create a new task 
	 * @param task - take parameter task and save
	 */
	@RequestMapping(value="/")
	public boolean createTask(Task task){
		return taskService.createTask(task);
	}
	
	/**
	 * get all users task according to id
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getUser/{id}")
	@ResponseBody
	public List getUserTask(@PathVariable int userId){
		
		return taskService.getUserTask(userId);
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param dates - date as a string  
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getUser/{id}/{date}")
	@ResponseBody
	public List getUserTaskByDate(@PathVariable("id") int userId,@PathVariable("date") String dates){
		System.out.println("this is in /getUser/{id}/{date}");
		System.out.println(userId);
		System.out.println(dates);
		Calendar calendar= Calendar.getInstance();
		String[] s = dates.split("-");
		calendar.set(Calendar.YEAR, Integer.parseInt(s[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(s[1]));
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(s[2]));
		Date date = calendar.getTime();
		List list = taskService.getUserByDate(userId,date);
		return list;
	}
}
