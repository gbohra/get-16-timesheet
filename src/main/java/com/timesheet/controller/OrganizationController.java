package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.timesheet.service.OrganizationService;
import com.timesheet.vo.OrganizationVO;
/**
 * 
 * This is handle requests of Organization
 */
@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
@RequestMapping(value="/api/v1/organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private ApplicationContext ApplicationContext;
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	public ApplicationContext getApplicationContext() {
		return ApplicationContext;
	}
	public void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContext = applicationContext;
	}
	/**
	 * This function add a organization
	 * @param organizationVO
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public OrganizationVO addOrganizaion(@RequestBody @Validated OrganizationVO organizationVO , BindingResult bindingResult){
		System.out.println("Binding res"+bindingResult);
		System.out.println(organizationVO);
		if (bindingResult.hasErrors()) {
			return organizationVO;
		} else {
			return organizationService.addOrganizaion(organizationVO);
		}
	}
	/**
	 * 
	 * @param id - user's id
	 * @return return OrganizationVO with id: id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public OrganizationVO getOrganizationByOrgId(@PathVariable("id") int id){
		OrganizationVO organizationVO = ApplicationContext.getBean(OrganizationVO.class);
		organizationVO.setId(id);
		return organizationService.getOrganization(organizationVO);
	}
	
	/**
	 * 
	 * @param id - user's id
	 * @return return OrganizationVO with id: id
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public OrganizationVO updateOrganization(@PathVariable("id") int id ,@RequestBody @Validated OrganizationVO organizationVO , BindingResult bindingResult){
		organizationVO.setId(id);
		if (bindingResult.hasErrors()) {
			return organizationVO;
		} else {
		return organizationService.updateOrganization(organizationVO);
		}
	}
	
	/**
	 * @return return List of OrganizationVO
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List getOrganizationsByUserId(){
		return organizationService.getOrganizations();
	}
	
	/**
	 * this method use for get organization's all project
	 * @param id
	 */
	@RequestMapping(value="/{id}/projects")
	public void getOrganizationProject(@PathVariable("id") int id){
		 organizationService.getOrganizationProject(id);
	}
}
