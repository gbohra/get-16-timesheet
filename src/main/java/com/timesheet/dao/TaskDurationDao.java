package com.timesheet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.dao.model.TaskDurationModel;

@Repository
@Transactional
public class TaskDurationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public TaskDurationModel insertOnNewTaskCreation(TaskDurationModel taskDurationModel) {
		 return insert(taskDurationModel);
	}
	
	private TaskDurationModel insert(TaskDurationModel taskDurationModel) {
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.save(taskDurationModel);
			return taskDurationModel;
		}catch(Exception e){
			e.printStackTrace();
			 return null;
		}
	}
	
	public TaskDurationModel update(TaskDurationModel taskDurationModel) {
		Session session = this.sessionFactory.getCurrentSession();
		//int taskId = taskDurationModel.getTaskId();
		Query q = session.createQuery("from TaskDurationModel where taskId=:taskId");
		
		String hql = "From TaskDurationModel Where taskId=:taskId";
		try{
			
			session.update(taskDurationModel);
			return taskDurationModel;
		}catch(Exception e){
			e.printStackTrace();
			 return null;
		}
	}
	
	public TaskDurationModel getById(int taskId) {
		Session session = this.sessionFactory.getCurrentSession();
		try{
			Query query = session.createQuery("from TaskDurationModel where taskId=:taskId");
			query.setParameter("taskId", taskId);
			return (TaskDurationModel)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
			 return null;
		}
	}
	
	

}
