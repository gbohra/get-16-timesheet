/**
 * 
 */
package com.timesheet.utill;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.timesheet.dao.model.Organization;
import com.timesheet.dao.model.ProjectModel;
import com.timesheet.vo.ProjectVO;

/**
 * @author admin
 *
 */
public final class BasicAuthorization {
	
	private static HttpServletRequest httpRequest;
	private static String token;
	private static TokenInfo tokenInfo;
		
	public static void setRequest(ServletRequest request){
		httpRequest = (HttpServletRequest) request;
		token = httpRequest.getHeader("token");
		tokenInfo = JWTTokenUtill.getDecrypt(token);
	}
	
	public static boolean isOrganizationReadAllowed(Organization organization, ServletRequest request){
		setRequest(request);
		boolean isAllowed = false;
		isAllowed = tokenInfo.getId() == organization.getCreatedBy(); /*|| organization member*/ 
		return isAllowed;
	}
	public static boolean isOrganizationUpdateAllowed(Organization organization, ServletRequest request){
		setRequest(request);
		boolean isAllowed = false;
		isAllowed = tokenInfo.getId() == organization.getCreatedBy();  
		return isAllowed;
	}
	
	public static boolean isProjectUpdateAllowed(ProjectVO projectVO, ServletRequest request){
		setRequest(request);
		boolean isAllowed = false;
		isAllowed = (tokenInfo.getId() == projectVO.getCreatedBy()); /* || user is a co-owner of project */
		return isAllowed;
	}
	
	public static boolean isProjectDeleteAllowed(ProjectVO projectVO, ServletRequest request){
		setRequest(request);
		boolean isAllowed = false;
		isAllowed = (tokenInfo.getId() == projectVO.getCreatedBy()); /* || user is a co-owner of project */
		return isAllowed;
	}
	
	public static boolean isProjectReadAllowed(ProjectVO projectVO, ServletRequest request){
		setRequest(request);
		boolean isAllowed = false;
//		isAllowed = (tokenInfo.getId() == projectVO.getCreatedBy()); /* || user is a co-owner of project || user is a member of the project*/
		return true;
	}

}
