package com.timesheet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CommonUtil {
	
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
