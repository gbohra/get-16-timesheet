package com.timesheet.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.timesheet.service.ApplicationContextProvider;
import com.timesheet.service.CurrentUserService;
import com.timesheet.utill.JWTTokenUtill;
import com.timesheet.utill.TokenInfo;


public class LoginInterceptor implements Filter {
	
	private ServletContext servletContext; 
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String JWTtoken = ((HttpServletRequest) request).getHeader("token");
		
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ApplicationContextProvider appContextProvider = new ApplicationContextProvider();
		appContextProvider.setApplicationContext(applicationContext);
		
		CurrentUserService currentUserService = (CurrentUserService) applicationContext.getBean("currentUserService");
		
		
		if(JWTtoken != null){
			// decrypt the JWTtoken
			TokenInfo tokenInfo = JWTTokenUtill.getDecrypt(JWTtoken);
			currentUserService.setJWTtoken(JWTtoken);
			if(tokenInfo != null){
				// get access token
				// validate access token via google or application oauth provider
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
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		servletContext = filterConfig.getServletContext();		
	}
}