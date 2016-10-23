package com.timesheet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.TaskDao;
import com.timesheet.dao.TaskDurationDao;
import com.timesheet.dao.model.Task;
import com.timesheet.dao.model.TaskDurationModel;
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
		taskDurationModel.setTaskId(taskVO.getId());
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
	@SuppressWarnings("rawtypes")
	public List getTask(int id){
		return taskDao.getTask(id);
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param date - date   
	 */
	@SuppressWarnings("rawtypes")
	public List getUserByDate(int userId,Date date){
		return taskDao.getTaskByDate(userId, date);
	}
}
