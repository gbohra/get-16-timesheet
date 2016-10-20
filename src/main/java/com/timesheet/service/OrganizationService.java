package com.timesheet.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.OrganizationDao;
import com.timesheet.dao.model.Organization;
import com.timesheet.vo.OrganizationVO;

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

	
	protected void populateVOIntoModel(OrganizationVO organizationVO, Organization organizationModel) {
		BeanUtils.copyProperties(organizationVO, organizationModel);
    }

    protected void populateModelIntoVO(OrganizationVO organizationVO, Organization organizationModel) {
    	BeanUtils.copyProperties(organizationModel , organizationVO);
    }

	/**
	 * This method is use to insert User object in data base 
	 * @param organization
	 */
	public OrganizationVO addOrganizaion(OrganizationVO organizationVO){
		Organization organizationModel =  new Organization() ;
		populateVOIntoModel(organizationVO, organizationModel);
		organizationModel = organizationDao.addOrganization(organizationModel);
		populateModelIntoVO(organizationVO, organizationModel);
		return organizationVO;
	}
	
	/**
	 * This method is use to get all organization from the user
	 * @param id -- user's id
	 * @return List of Organization
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizations(){
		return organizationDao.getOrganizations();
	}
	
	/**
	 * This method is use to get all organization from the user
	 * @param id -- user's id
	 * @return List of Organization
	 */
	public Organization updateOrganization(Organization o){
		return organizationDao.updateOrganization(o);
	}
	
	/**
	 * This method is use to get all organization from the user
	 * @param id -- user's id
	 * @return List of Organization
	 */
	@SuppressWarnings("unchecked")
	public Organization getOrganization(int id){
		return organizationDao.getOrganization(id);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void getOrganizationProject(int id){
		// organizationDao.getOrganizationProject(id);
	}

}
