package com.timesheet.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.Task;
import com.timesheet.service.TaskService;
import com.timesheet.utill.JWTTokenUtill;
import com.timesheet.utill.TokenInfo;
@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
@RequestMapping(value="/api/v1")
public class TaskController {
	@Autowired
	TaskService taskService;
	
	/**
	 * This create a new task 
	 * @param task - take parameter task and save
	 */
	@RequestMapping(value="/task",method=RequestMethod.POST)
	@ResponseBody
	public Task createTask(@RequestBody Task task){
		return taskService.createTask(task);
	}
	
	
	@RequestMapping(value="/task",method=RequestMethod.PUT)
	@ResponseBody
	public Task updateTask(@RequestBody Task task){
		return taskService.updateTask(task);
	}
	
	
	
	
	/**
	 * get all users task according to id
	 * @param userId - users id 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/user/{id}/task",method=RequestMethod.GET)
	@ResponseBody
	public List getTask(@PathVariable("id") int userId){
		System.out.println("now i am in controller");
		TokenInfo info = new TokenInfo();
		info.setAccessToken("fhduyfgdvyughdvyregvhrvyuervg");
		info.setEmail("ygshsdvyv");
		info.setId(1);
		info.setName("avinash");
		String encryptedText = JWTTokenUtill.getEncrypted(info);
		System.out.println("let us test it"+JWTTokenUtill.getEncrypted(info));
		TokenInfo s = JWTTokenUtill.getDecrypt(encryptedText);
		System.out.println("this is encrpted name"+s.getName());
		System.out.println("this is enc email"+s.getEmail());
		System.out.println(""+userId);
		return taskService.getTask(userId);
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param dates - date as a string  
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/user/{id}/{date}/task",method=RequestMethod.GET)
	@ResponseBody
	public List getTaskByDate(@PathVariable("id") int userId,@PathVariable("date") String dates){
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
