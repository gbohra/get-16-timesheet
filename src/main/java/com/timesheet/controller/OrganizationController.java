package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.Organization;
import com.timesheet.service.OrganizationService;
/**
 * 
 * @author Avinash
 * This is handle requests of Organization
 */
@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
@RequestMapping(value="/api/v1/organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	/**
	 * This function add a organization
	 * @param organization 
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	@ResponseBody
	public Organization addOrganizaion(@RequestBody Organization organization){
		System.out.println(organization);
		return organizationService.addOrganizaion(organization);
	}
	/**
	 * 
	 * @param id - user's id
	 * @return return Organization with id: id
	 */
	@RequestMapping(value="/{id}")
	@ResponseBody
	public Organization getOrganization(@PathVariable("id") int id){
		return organizationService.getOrganization(id);
	}
	
	/**
	 * 
	 * @param id - user's id
	 * @return return Organization with id: id
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Organization updateOrganization(@RequestBody Organization organization){
		System.out.println("this is "+organization);
		
		return organizationService.updateOrganization(organization);
	}
	
	/**
	 * 
	 * @param id - user's id
	 * @return return List of Organization
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<Organization> getOrganizations(){
		return organizationService.getOrganizations();
	}
	
	/**
	 * this method use for get arganization's all project
	 * @param id
	 */
	@RequestMapping(value="/{id}/projects")
	public void getOrganizationProject(@PathVariable("id") int id){
		 organizationService.getOrganizationProject(id);
	}
}
