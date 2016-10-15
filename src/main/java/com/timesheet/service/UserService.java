package com.timesheet.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timesheet.dao.UserDao;
import com.timesheet.dao.model.UserModel;
@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	
	
	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}
	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * @param user
	 * @return  true if insert successfully,else false
	 */
	public boolean insert(UserModel user){
		System.out.println("this is user service");
		return userDao.insert(user);
	}
	/**
	 * @param user 
	 * @return true if delete successfully,else false
	 */
	public boolean deleteUser(UserModel user){
		return userDao.delete(user);				
	}
	/**
	 * 
	 * @param id user's id 
	 * @return list of projects belong to this user
	 */
	/*public List<Projects> userProjects(int id){
		return userDao.getUserProjects(id);
	}*/
	
	/**
	 * 
	 * @param user 
	 * @return  true if update successfully,else false
	 */
	public boolean update(UserModel user){
		return userDao.update(user);
	}
	
	
	/**
	 * 
	 * @param id - users id
	 * @return - UserModel if user present 
	 */
	public UserModel getUserData(int id){
		System.out.println("this is get uxr kjv");
		return userDao.getUserInfo(id);
	}
	

}
