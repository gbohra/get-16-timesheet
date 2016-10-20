package com.timesheet.utill;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class TokenData {
	HttpServletRequest httpRequest ;
	String token;
	TokenInfo tokenInfo;
	public TokenData(ServletRequest request){
		this.httpRequest  = (HttpServletRequest) request;
		this.token= httpRequest.getHeader("token");
		this.tokenInfo = JWTTokenUtill.getDecrypt(token);
		
	}
	
	public String getName(){
		
		
		return this.tokenInfo.getName();
	}
	
	public String getEmail(){
		
		return this.tokenInfo.getEmail();
	}
	
	public int getUserId(){
		
		return this.tokenInfo.getId();
	}

	

}
