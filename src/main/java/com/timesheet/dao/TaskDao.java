package com.timesheet.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.dao.model.ProjectModel;
import com.timesheet.dao.model.Task;
import com.timesheet.dao.model.TaskDurationModel;
/**
 * This 
 * @author Avinash
 *
 */
@Repository
@Transactional 
public class TaskDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This create a new task 
	 * @param task - take parameter task and save
	 */
	public Task createTask(Task task){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.save(task);
			return task;
		}catch(Exception e){
			e.printStackTrace();
			 return null;
		}
	}
	
	
	
	public Task updateTask(Task task){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.update(task);
			return task;
		}catch(Exception e){
			e.printStackTrace();
			 return null;
		}
	}
	
	
	
	
	/**
	 * return a user's all task
	 * @param userId - user's id
	 * @return - list of user's task
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Task> getTaskByUserId(int userId){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "FROM Task as t where t.createdBy = :userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			List<Task> listOfTask = (List<Task>) query.list();
			return listOfTask;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param date - date   
	 */
	@SuppressWarnings("rawtypes")
	public List getTaskByDate(int userId,Long date){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM Task as t where t.createdBy = :userId AND t.taskDurationModel.date = :date";
		try{
			
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("date", date);
			List<Task> list = (List<Task>) query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}			
	}
	
	public List getMyTimesheet(Long fromMonth, Long toMonth, int userId){
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM Task as t join t.taskDurationModel td where t.createdBy = :userId AND td.date BETWEEN :fromMonth AND :toMonth";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("fromMonth", fromMonth);
			query.setParameter("toMonth", toMonth);
			List list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
		
	}
}
