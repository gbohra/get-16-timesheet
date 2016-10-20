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

import org.springframework.web.filter.GenericFilterBean;

@WebFilter({"/"/*,"/api","/api/v1/user/","/api/v1/"*/})
public class LoginInterceptor extends GenericFilterBean {


	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
		System.out.println("yhis is fo dilter");

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);
		try {
			
			String email = (String)session.getAttribute("email");
			if (session == null || email == null) {
				System.out.println("session is null");
				httpResponse.sendRedirect("");
			}
			filterChain.doFilter(request, response);
		} catch(NullPointerException npe) {
			httpResponse.sendRedirect("");
		}
	}
}