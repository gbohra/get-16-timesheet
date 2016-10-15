package com.timesheet.controller;

import java.sql.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timesheet.dao.UserDao;
import com.timesheet.dao.model.UserModel;
import com.timesheet.service.UserService;


@Controller
@RequestMapping(value="/timesheet")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * This is use to insert user detail into user's table.
	 * This method send data to DAO
	 * @param user - user detail pojo 
	 */
	@RequestMapping(value="/users")
	public boolean insert(/*UserModel user*/){
		//System.out.println("this is user controller");
		UserModel user = new UserModel();
		user.setId(1);
		user.setFirstName("fdjvdffb");
		user.setLastName("vbbvu");
		user.setEmail("hbdfbvfvb");
		user.setPassword("kfjdbjbv");
		Date date = new Date(2000,1,1);
		user.setCreated_date(date);
		user.setUpdated_date(date);
		System.out.println("controler" +user);
		return userService.insert(user);	
	}
	/**
	 * This is use to Delete user detail into user's table.
	 * This method send data to DAO
	 * @param user - user detail pojo 
	 */
	@RequestMapping(name="/users",method=RequestMethod.DELETE)
	public boolean delete(UserModel user){
		return userService.insert(user);	
	}

	/**
	 * 
	 * @param user
	 * @return  true if update successfully,else false
	 */
	@RequestMapping(name="/users/{id}",method=RequestMethod.PUT)
	public boolean update(UserModel user){
		return userService.update(user);
	}
	
	/**
	 * 
	 * @param id - users id
	 * @return - UserModel if user present 
	 */
	@RequestMapping(value="/users/{id}")
	public UserModel getUserData(@PathVariable("id")int id){
		//System.out.println("this is get user data");
		//System.out.println("this is id");
		System.out.println("this is get user contrller ");
		System.out.println(userService.getUserData(id));
		return userService.getUserData(id);
	}
	/*
	@RequestMapping(name="/users/{id}/projects")
	public void getUserProjects(@PathVariable("id")int id){
		userDAO.getUserProjects(id);
	}
	*/
}
