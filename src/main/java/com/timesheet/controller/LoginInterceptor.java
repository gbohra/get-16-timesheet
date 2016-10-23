package com.timesheet.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timesheet.utill.JWTTokenUtill;
import com.timesheet.utill.TokenInfo;


public class LoginInterceptor implements Filter {
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String JWTtoken = ((HttpServletRequest) request).getHeader("token");
		
	
		System.out.println("TOKEN = "+ ((HttpServletRequest) request).getHeader("token"));
		System.out.println("do filter");
		
		if(JWTtoken != null){
			// decrypt the JWTtoken
			TokenInfo tokenInfo = JWTTokenUtill.getDecrypt(JWTtoken);
			if(tokenInfo != null){
				// get access token
				// validate access token via google or oauth provider
				filterChain.doFilter(request, response);
			}else{
				redirectToLogin(request, response, filterChain);
			}
		}else{
			System.out.println("Else Part");
//			redirectToLogin(request, response, filterChain);
		}
			
	}
	
	private void redirectToLogin(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("Hello ");
		httpResponse.sendRedirect("/");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destrpoy");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("INIT");
		
	}
}