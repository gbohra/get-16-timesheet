package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.UserModel;
import com.timesheet.service.UserService;
//@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping(value="api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * This is use to insert user detail into user's table.
	 * This method send data to DAO
	 * @param user - user detail pojo 
	 */
	@RequestMapping(value="/")
	@ResponseBody
	public String insert(UserModel user){
		if (userService.insert(user)){
			return "success";
		}
		else{
			return "failed";
		}
	}
	/**
	 * This is use to Delete user detail into user's table.
	 * This method send data to DAO
	 * @param user - user detail pojo 
	 */
	@RequestMapping(name="/",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean delete(UserModel user){
		return userService.insert(user);	
	}

	/**
	 * 
	 * @param user
	 * @return  true if update successfully,else false
	 */
	@RequestMapping(name="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public boolean update(UserModel user){
		return userService.update(user);
	}
	
	
	/**
	 * 
	 * @param id - users id
	 * @return - UserModel if user present 
	 */
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List getUserData(@PathVariable("id")int id){
		//System.out.println("this is get user data");
		//System.out.println("this is id");
		System.out.println("this is get user contrller " + id);
		return userService.getUserData(id);
	}
	/*
	@RequestMapping(name="/users/{id}/projects")
	public void getUserProjects(@PathVariable("id")int id){
		userDAO.getUserProjects(id);
	}
	*/
}
