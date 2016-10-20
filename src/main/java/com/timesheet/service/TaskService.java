package com.timesheet.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timesheet.dao.TaskDao;
import com.timesheet.dao.model.Task;
import com.timesheet.vo.TaskVO;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	
	public TaskDao getTaskDao() {
		return taskDao;
	}


	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
   /**
    * to convert TaskVO to TaskModel
    */
	protected void populateVOIntoModel(TaskVO taskvo, Task taskModel) {
		BeanUtils.copyProperties(taskvo, taskModel);
    }
	/**
	 * This create a new task  
	 * @param task - take parameter task and save
	 */
	public boolean createTask(TaskVO taskvo){
		Task taskModel =  new Task() ;
		populateVOIntoModel(taskvo, taskModel);
		return taskDao.createTask(taskModel);
	}
	
	
	/**
	 * return a user's all task
	 * @param userId - user's id
	 * @return - list of user's task
	 */
	@SuppressWarnings("rawtypes")
	public List getUserTask(int id){
		return taskDao.getTask(id);
	}
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param date - date   
	 */
	@SuppressWarnings("rawtypes")
	public List getUserByDate(int userId,Date date){
		return taskDao.getUserTaskByDate(userId, date);
	}

}
