package com.timesheet.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.dao.model.UserModel;


@Repository()
@Transactional
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 public SessionFactory getSessionFactory() {
	        return sessionFactory;
	 }
	 
	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	 }
	/**
	 * 
	 * @param user - user detail object 
	 * @return true if object save successfully else return false.
	 */
	public boolean insert(UserModel user){
		System.out.println("this is user dao");
		try{
		// creating a session 	
		Session session = this.sessionFactory.getCurrentSession();
		 session.save(user);
		 return true;
		}
		catch(Exception e){
			
			return false;
		}
	}
	
	/**
	 * 
	 * @param user - user object contain user detail
	 * @return - true if delete successfully else return false
	 */
	
	public boolean delete(UserModel user){
		
		try{
			// creating a session
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(user);
				return true;
			}
			catch(Exception e){
				
				return false;
			}
	}  

	/**
	 * 
	 * @param id - user's id
	 * @return user's detail
	 */
	@SuppressWarnings("unchecked")
	public UserModel getUserInfo(int id){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select firstName from UserModel where id=1");
		List<UserModel> list =  query.list();
		System.out.println(list.get(0));  
		return null;
	}
	 
	/**
	 * 
	 * @param id -users id
	 * @return List of project belong to this user
	 */
	/*public List<Projects> getUserProjects(int id){
		Session session = this.sessionFactory.getCurrentSession();
		//List<Project> project = (project) session.createCriteria(Project.class).add(Restrictions.eq("created_by",id)).list();
		return projects
	}*/
	
	/**
	 * 
	 * @param user 
	 * @return  true if update successfully,else false
	 */
	public boolean update(UserModel user){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.update(user);
			return true;
		}
		catch(Exception e){
			// logs
			return false;
		}
	}
	

}
