package com.timesheet.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.service.TaskService;
import com.timesheet.vo.TaskVO;
@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
@RequestMapping(value="/api/v1/tasks")
public class TaskController {
	@Autowired
	TaskService taskService;
	
	/**
	 * This create a new task 
	 * @param task - take parameter task and save
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public boolean createTask(@RequestBody @Validated TaskVO taskvo,  BindingResult bindingResult){
		System.out.println("this is task"+taskvo);
		if (bindingResult.hasErrors()) {
		return true;
	}
		else{
			return taskService.createTask(taskvo);
		}
		}
	/**
	 * get all users task according to id
	 * @param userId - users id 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List getUserTask(@PathVariable("id") int userId){
		System.out.println("this is dhbsdb"+userId);
		return taskService.getUserTask(userId);
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param dates - date as a string  
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/{id}/{date}",method=RequestMethod.GET)
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
