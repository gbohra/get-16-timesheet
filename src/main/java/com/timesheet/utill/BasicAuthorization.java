/**
 * 
 */
package com.timesheet.utill;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.timesheet.dao.model.Organization;

/**
 * @author admin
 *
 */
public class BasicAuthorization {
	HttpServletRequest httpRequest;
	String token;
	TokenInfo tokenInfo;
	
	public BasicAuthorization(ServletRequest request){
		this.httpRequest = (HttpServletRequest) request;
		this.token = httpRequest.getHeader("token");
		this.tokenInfo = JWTTokenUtill.getDecrypt(token);
	}
	
	public boolean organizationRead(Organization organization){
		boolean isAllowed = false;
		isAllowed = this.tokenInfo.getId() == organization.getCreatedBy(); /*|| organization member*/ 
		return isAllowed;
	}
	public boolean organizationUpdate(Organization organization){
		boolean isAllowed = false;
		isAllowed = this.tokenInfo.getId() == organization.getCreatedBy();  
		return isAllowed;
	}

}
