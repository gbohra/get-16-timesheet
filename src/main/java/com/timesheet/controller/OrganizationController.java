package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.timesheet.dao.model.Organization;
import com.timesheet.service.OrganizationService;

@Controller
@RequestMapping(value="/timesheet/Organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	/**
	 * This function add a organization
	 * @param organization 
	 */
	@RequestMapping(value="/")
	public void addOrganizaion(Organization organization){
		
	}
	/**
	 * 
	 * @param id - user's id
	 * @return return List of Organization
	 */
	@RequestMapping(value="/{id}")
	public List<Organization> getUserOrganization(@PathVariable int id){
		return organizationService.getUserOrganization(id);
	}
	
	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}/projects")
	public void getOrganizationProject(@PathVariable int id){
		organizationService.getOrganizationProject(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
