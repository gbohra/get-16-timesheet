package com.timesheet.controller;

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
import com.timesheet.vo.TaskVO;
//@CrossOrigin(origins = "http://192.168.100.113:3000")
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
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/me",method=RequestMethod.GET)
	@ResponseBody
	public List getTask(){
		int userId = 1;
//		System.out.println("now i am in controller");
//		TokenInfo info = new TokenInfo();
//		info.setAccessToken("fhduyfgdvyughdvyregvhrvyuervg");
//		info.setEmail("ygshsdvyv");
//		info.setId(1);
//		info.setName("avinash");
//		String encryptedText = JWTTokenUtill.getEncrypted(info);
//		System.out.println("let us test it"+JWTTokenUtill.getEncrypted(info));
//		TokenInfo s = JWTTokenUtill.getDecrypt(encryptedText);
//		System.out.println("this is encrpted name"+s.getName());
//		System.out.println("this is enc email"+s.getEmail());
//		System.out.println(""+userId);
		return taskService.getTask(userId);
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param dates - date as a string  
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/me/{date}",method=RequestMethod.GET)
	@ResponseBody
	public List getTaskByDate(@PathVariable("date") Long dates){
		
		
//		System.out.println("this is in /getUser/{id}/{date}");
//		System.out.println(userId);
//		System.out.println(dates);
//		Calendar calendar= Calendar.getInstance();
//		String[] s = dates.split("-");
//		calendar.set(Calendar.YEAR, Integer.parseInt(s[0]));
//		calendar.set(Calendar.MONTH, Integer.parseInt(s[1]));
//		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(s[2]));
//		Date date = calendar.getTime();
//		List list = taskService.getUserByDate(userId,date);
//		return list;
		return null;
	}
}
