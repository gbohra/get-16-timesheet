/**
 * 
 */
package com.timesheet.utill;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.timesheet.dao.model.Organization;
import com.timesheet.dao.model.ProjectModel;

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
	
	public boolean isProjectUpdateAllowed(ProjectModel projectModel){
		boolean isAllowed = false;
		isAllowed = (this.tokenInfo.getId() == projectModel.getCreatedBy()); /* || user is a co-owner of project */
		return isAllowed;
	}
	
	public boolean isProjectDeleteAllowed(ProjectModel projectModel){
		boolean isAllowed = false;
		isAllowed = (this.tokenInfo.getId() == projectModel.getCreatedBy()); /* || user is a co-owner of project */
		return isAllowed;
	}
	
	public boolean isProjectReadAllowed(ProjectModel projectModel){
		boolean isAllowed = false;
//		isAllowed = (this.tokenInfo.getId() == projectModel.getCreatedBy()); /* || user is a co-owner of project || user is a member of the project*/
		return true;
	}

}
