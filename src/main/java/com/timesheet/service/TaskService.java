package com.timesheet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.TaskDao;
import com.timesheet.dao.TaskDurationDao;
import com.timesheet.dao.model.Task;
import com.timesheet.dao.model.TaskDurationModel;
import com.timesheet.utill.CommonUtil;
import com.timesheet.vo.TaskVO;

@Service
public class TaskService {

	public TaskDurationDao getTaskDurationDao() {
		return taskDurationDao;
	}


	public void setTaskDurationDao(TaskDurationDao taskDurationDao) {
		this.taskDurationDao = taskDurationDao;
	}


	public TaskDurationModel getTaskDurationModel() {
		return taskDurationModel;
	}


	public void setTaskDurationModel(TaskDurationModel taskDurationModel) {
		this.taskDurationModel = taskDurationModel;
	}


	public Task getTask() {
		return task;
	}


	public void setTask(Task task) {
		this.task = task;
	}
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private TaskDurationDao taskDurationDao;
	
	@Autowired
	private TaskDurationModel taskDurationModel;
	
	@Autowired
	private Task task;
	
	
	public TaskDao getTaskDao() {
		return taskDao;
	}


	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}


	/**
	 * This create a new task  
	 * @param task - take parameter task and save
	 */
	public TaskVO createTask(TaskVO taskVO){
		//convert VO to Model
		populateVOIntoModel(taskVO);
		 task = taskDao.createTask(task);
		 taskDurationModel.setTaskId(task.getId());
		 taskDurationModel = taskDurationDao.insertOnNewTaskCreation(taskDurationModel);
		 return populateModelIntoVO(taskVO);
	}
	
	public void populateVOIntoModel(TaskVO taskVO) {
		task.setId(taskVO.getId());
		task.setCreatedBy(taskVO.getCreatedBy());
		task.setCreatedDate(taskVO.getCreatedDate());
		task.setDescription(taskVO.getDescription());
		task.setPriority(taskVO.getPriority());
		task.setRepeatFrequency(taskVO.getRepeatFrequency());
		task.setStatus(taskVO.getStatus());
		task.setSubTask(taskVO.getSubTask());
		taskDurationModel.setTaskId(task.getId());
		taskDurationModel.setDate(taskVO.getDate());
		taskDurationModel.setDuration(taskVO.getDuration());
	}
	
	public TaskVO populateModelIntoVO(TaskVO taskVO) {
		taskVO.setId(task.getId());
		taskVO.setCreatedBy(task.getCreatedBy());
		taskVO.setCreatedDate(task.getCreatedDate());
		taskVO.setDescription(task.getDescription());
		taskVO.setPriority(task.getPriority());
		taskVO.setRepeatFrequency(task.getRepeatFrequency());
		taskVO.setStatus(task.getStatus());
		taskVO.setSubTask(task.getSubTask());
		taskVO.setDate(taskDurationModel.getDate());
		taskVO.setDuration(taskDurationModel.getDuration());
		return taskVO;
	}
	
	
	public TaskVO updateTask(TaskVO taskVO){
		//convert VO to Model
				populateVOIntoModel(taskVO);
				 task = taskDao.updateTask(task);
				 taskDurationModel.setTaskId(task.getId());
				 taskDurationModel.setId((taskDurationDao.getById(task.getId())).getId());
				 taskDurationModel = taskDurationDao.update(taskDurationModel);
				 return populateModelIntoVO(taskVO);
	}
	
	
	/**
	 * return a user's all task
	 * @param userId - user's id
	 * @return - list of user's task
	 */

	public List<Task> getTaskMe(){
		return taskDao.getTaskByUserId(CurrentUserService.getUserModel().getId());
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param date - date   
	 */
	@SuppressWarnings("rawtypes")
	public List getTaskByDate(Long d){
		Date date = new Date(d);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(1);
//		return taskDao.getTaskByDate(CurrentUserService.getUserModel().getId(), date.getTime());
		return getTasksByDate(date.getTime(), taskDao.getTaskByUserId(CurrentUserService.getUserModel().getId()));
	}
	
	public List getMyTimesheet() {
		Date date = CommonUtil.dateNow();
		date.setDate(1);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		Long fromMonth = date.getTime();
		date.setMonth(date.getMonth()+1);;
		Long toMonth = date.getTime();
		
		
//		return taskDao.getMyTimesheet(fromMonth, toMonth, CurrentUserService.getUserModel().getId());
		return getMyTimeSheet(fromMonth, toMonth, taskDao.getTaskByUserId(CurrentUserService.getUserModel().getId()));
	}
	
	
//	Temporary method to filter tasks on date
	public List<Task> getTasksByDate(Long date, List<Task> myTasks){
		List<Task> filteredList = new ArrayList<Task>();
		for (Task task : myTasks) {
			for (TaskDurationModel taskDurationModel : task.getTaskDurationModel()) {
//				/1000 to remove milliseconds 
				if((date/1000) == (taskDurationModel.getDate()/1000)){
					filteredList.add(task);
				}
			}
		}
		return filteredList;
	}
	
//	Temporary method to filter timesheet tasks
	public List<Task> getMyTimeSheet(Long fromMonth, Long toMonth, List<Task> myTasks){
		List<Task> filteredList = new ArrayList<Task>();
		for (Task task : myTasks) {
			for (TaskDurationModel taskDurationModel : task.getTaskDurationModel()) {
				if(taskDurationModel.getDate().longValue() >= fromMonth.longValue() && taskDurationModel.getDate().longValue() <= toMonth.longValue()){
					filteredList.add(task);
				}
			}
		}
		return filteredList;
	}
}
