package com.timesheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.OrganizationDao;
import com.timesheet.dao.model.Organization;

@Service
public class OrganizationService {
	
	@Autowired
	OrganizationDao organizationDao;
	
	/**
	 * This is getter of organizationDao
	 * @return organizationDao 
	 */
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}


	/**
	 * This is setter of organizationDao
	 * @param organizationDao 
	 */
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}


	/**
	 * This method is use to insert User object in data base 
	 * @param organization
	 */
	public boolean addOrganizaion(Organization organization){
		return organizationDao.addOrganization(organization);
	}
	
	/**
	 * This method is use to get all organization from the user
	 * @param id -- user's id
	 * @return List of Organization
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getUserOrganization(int id){
		return organizationDao.getUserOrganization(id);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void getOrganizationProject(int id){
		// organizationDao.getOrganizationProject(id);
	}

}
