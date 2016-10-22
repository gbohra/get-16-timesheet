package com.timesheet.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author Avinash 
 * All the common things in application are come here 
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/api/v1/commonUtil")
public class CommonUtil {
	
	@RequestMapping(value="/getDate")
	public Date getDate(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
	
	public boolean checkSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		if(session != null){
			if(session.getAttribute("email") != null){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	

}
