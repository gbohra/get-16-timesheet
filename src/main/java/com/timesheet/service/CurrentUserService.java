package com.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.UserDao;
import com.timesheet.dao.model.UserModel;
import com.timesheet.utill.JWTTokenUtill;
import com.timesheet.utill.TokenInfo;

@Service
public class CurrentUserService {
	
	@Autowired
	private UserDao userDao;
	
	private static UserModel userModel;
	
	private String JWTtoken;
	
	public static UserModel getUserModel() {
		return userModel;
	}

	public void setJWTtoken(String jWTtoken) {
		this.JWTtoken = jWTtoken;
		setCurrentUserModel();
	}
	
	private void setCurrentUserModel() {
		TokenInfo tokenInfo = JWTTokenUtill.getDecrypt(this.JWTtoken);
		userModel = userDao.getUserInfo(tokenInfo.getId());
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String getJWTtoken() {
		return this.JWTtoken;
	}
}
