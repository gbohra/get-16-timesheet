package com.timesheet.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.timesheet.dao.OrganizationDao;
import com.timesheet.dao.model.OrganizationModel;
import com.timesheet.vo.OrganizationVO;

@Service
public class OrganizationService {
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}


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

	
	protected void populateVOIntoModel(OrganizationVO organizationVO, OrganizationModel organizationModel) {
		BeanUtils.copyProperties(organizationVO, organizationModel);
    }

    protected void populateModelIntoVO(OrganizationVO organizationVO, OrganizationModel organizationModel) {
    	BeanUtils.copyProperties(organizationModel , organizationVO);
    }

	/**
	 * This method is use to insert User object in data base 
	 * @param organization
	 */
	public OrganizationVO addOrganizaion(OrganizationVO organizationVO){
		OrganizationModel organizationModel =  applicationContext.getBean(OrganizationModel.class);
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
	@SuppressWarnings({ "rawtypes" })
	public List getOrganizations(){
		return organizationDao.getOrganizations();
	}
	
	/**
	 * This method is use to get all organization from the user
	 * @param id -- user's id
	 * @return List of Organization
	 */
	public OrganizationVO updateOrganization(OrganizationVO organizationVO){
		OrganizationModel organizationModel =  applicationContext.getBean(OrganizationModel.class);
		populateVOIntoModel(organizationVO, organizationModel);
		organizationModel =organizationDao.updateOrganization(organizationModel);
		populateModelIntoVO(organizationVO, organizationModel);
		return organizationVO;
	}
	
	/**
	 * This method is use to get all organization from the user
	 * @param id -- user's id
	 * @return List of Organization
	 */
	public OrganizationVO getOrganization(OrganizationVO organizationVO){
		OrganizationModel organizationModel =  applicationContext.getBean(OrganizationModel.class);
		populateVOIntoModel(organizationVO, organizationModel);
		organizationModel =organizationDao.getOrganization(organizationModel);
		populateModelIntoVO(organizationVO, organizationModel);
		return organizationVO;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void getOrganizationProject(int id){
		// organizationDao.getOrganizationProject(id);
	}

}
