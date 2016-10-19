package com.timesheet.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.timesheet.dao.model.Task;

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
	public boolean createTask(Task task){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.save(task);
			return true;
		}catch(Exception e){
			 return false;
		}
	}
	/**
	 * return a user's all task
	 * @param userId - user's id
	 * @return - list of user's task
	 */
	@SuppressWarnings("rawtypes")
	public List getTask(int userId){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "FROM Task T WHERE T.created_by = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", userId);
			List list = query.list();
			 return list;
		}catch(Exception e){
			return null;
		}
	}
	
	
	/**
	 * getting user's task by usrs's id and date 
	 * @param userId user's id 
	 * @param date - date   
	 */
	@SuppressWarnings("rawtypes")
	public List getUserTaskByDate(int userId,Date date){
		System.out.println("this is user dao ");
		Session session = this.sessionFactory.getCurrentSession();	
		//String hql = "FROM Task t where createdBy = 1 and (DATEDIFF(DATE_ADD(createdDate,INTERVAL repeatFrequency DAY),createdDate) >  0)";
		String hql = "FROM Task";
		try{
			Query query = session.createQuery(hql);
			System.out.println("hql is executrd");
			List list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
				
	}

	

}
