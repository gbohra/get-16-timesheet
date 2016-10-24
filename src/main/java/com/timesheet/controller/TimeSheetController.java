package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.service.TaskService;
import com.timesheet.vo.TaskVO;

/**
 * get user time sheet 
* (( currently getting for one month ))
* ((can be extended to yearly or monthly or weekly depending upon future requirements))
 *
 */
@Controller
@RequestMapping(value="/api/v1/timesheets")
public class TimeSheetController {

	@Autowired
	private TaskService taskService;
	
	/**
	 * get user time sheet 
	 * (( currently getting for one month ))
	 * ((can be extended to yearly or monthly or weekly depending upon future requirements))
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/me",method=RequestMethod.GET)
	@ResponseBody
	public List getMyTimesheet() {
		 return taskService.getMyTimesheet();
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
