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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTask(int userId){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "FROM Task T WHERE T.createdBy = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", userId);
			List list =query.list() ;
			 return list;
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
	public List getUserTaskByDate(int userId,Date date){
		Session session = this.sessionFactory.getCurrentSession();	
		String hql = "FROM Task t where t.createdBy = :id and (DATEDIFF(DAY,DATE_ADD(createdDate,INTERVAL repeatFrequency DAY),:date) >  0)";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("id", userId);
			query.setParameter("date", date);
			List list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}			
	}
}
