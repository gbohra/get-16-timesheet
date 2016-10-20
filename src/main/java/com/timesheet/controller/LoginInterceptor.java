package com.timesheet.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.GenericFilterBean;

import com.timesheet.utill.JWTTokenUtill;
import com.timesheet.utill.TokenInfo;

@WebFilter({"/api/v1/"/*,"/api","/api/v1/user/","/api/v1/"*/})
public class LoginInterceptor extends GenericFilterBean {
	
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
		String JWTtoken = ((HttpServletRequest) request).getHeader("token");
		
		System.out.println("do filter");
		
		if(JWTtoken != null){
			// decrypt the JWTtoken
			TokenInfo tokenInfo = JWTTokenUtill.getDecrypt(JWTtoken);
			if(tokenInfo != null){
				// get access token
				// validate access token via google or oauth provider
			}else{
				redirectToLogin(request, response, filterChain);
			}
		}else{
			System.out.println("Else Part");
			redirectToLogin(request, response, filterChain);
		}
			
	}
	
	private void redirectToLogin(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("Hello ");
		httpResponse.sendRedirect("/");
	}
}