package com.timesheet.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.UserModel;


@Repository
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
	 @ResponseBody
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
	@ResponseBody
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
	
	@SuppressWarnings("rawtypes")
	public List getUserInfo(int id){
		System.out.println("this is user dao"+id);
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserModel m where m.id=:id");
		query.setParameter("id", id);
		List list =  (List) query.list();  
		return list;
	}
	 
	/**
	 * 
	 * @param id -users id
	 * @return List of project belong to this user
	 */
	
	//@ResponseBody
	/*public List getUserProjects(int id){
		Session session = this.sessionFactory.getCurrentSession();
		//List<Project> project = (project) session.createCriteria(Project.class).add(Restrictions.eq("created_by",id)).list();
		return projects
	}*/
	
	/**
	 * 
	 * @param user 
	 * @return  true if update successfully,else false
	 */
	@ResponseBody
	public boolean update(UserModel user){
		System.out.println("this is dao update method");
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
	
	public int checkEmail(String email){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "id FROM User u WHERE u.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List list = query.list();
			if(list.size() > 0){
				System.out.println("this is list's 0th element"+list.get(0));
				return (int) list.get(0);
			}
			else{
				return 0;
			}
			
		}
		catch(Exception e){
			return -1;
		}
	}

}
