package com.timesheet.controller;

//import all required classes
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.ProjectModel;
import com.timesheet.service.ProjectService;
import com.timesheet.utill.BasicAuthorization;

/**
 * Controller for accessing ProjectService
 * 
 * @author simran
 *
 */
@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
@RequestMapping("/api/v1/projects")
public class ProjectController {
	
	// create bean of ProjectService
	@Autowired
	private ProjectService projectService;
	
	/**
	 * get projectService
	 * 
	 * @return projectService : projectService reference
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * set projectService
	 * 
	 * @param projectService
	 *            : projectService reference
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

    /**
     *create a new project
     * @param projectModel
     * @return ResponseEntity<String> : String
     */
	@RequestMapping(value="",method = RequestMethod.POST)
	@ResponseBody
	public ProjectModel createProject(@RequestBody ProjectModel projectModel) {	
		projectModel.setCreatedBy(1);
		projectModel.setUpdatedBy(1);
		return projectService.createProject(projectModel);
	}
	
	/**
	 * update project details
	 * @param projectModel
	 * @param id : id of project to be updated
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public ProjectModel updateProject(@RequestBody ProjectModel projectTemp, ServletRequest request) {
		ProjectModel projectModel = projectService.getProjectDetails(projectTemp.getId());
		if(new BasicAuthorization(request).isProjectUpdateAllowed(projectModel)){
			System.out.println("this is update project");
			return projectService.updateProject(projectModel);
		}else{
			// error message should be show to front end
			return null; 
		}
	}
	
	/**
	 * delete a project
	 * @param id : id of project to be deleted
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id, ServletRequest request) {
		ProjectModel projectModel = projectService.getProjectDetails(id);
		if(new BasicAuthorization(request).isProjectDeleteAllowed(projectModel)){
			projectService.deleteProject(projectModel.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}else{
			// error message should be show to front end
			return null; 
		}  	
    }
	
	/**
	 * get project details by id
	 * @param id : id of project to be retrieved
	 * @return ResponseEntity<ProjectModel>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectModel getProjectDetails(@PathVariable("id") int id, ServletRequest request) {
		ProjectModel projectModel = projectService.getProjectDetails(id);
		if(new BasicAuthorization(request).isProjectReadAllowed(projectModel)){
			return projectModel;
		}else{
			// error message should be show to front end
			return null; 
		}  	
	}
	
	/**
	 * get list of all projects
	 * @return ResponseEntity<List<ProjectModel>>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/me", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProjectModel>> getAllProjects() {
		
		return new ResponseEntity<List<ProjectModel>>(projectService.getAllProjects(),
				HttpStatus.OK);
	}
	
/*	*//**
	 * get project details by id
	 * @param id : id of project to be retrieved
	 * @return ResponseEntity<ProjectModel>
	 *//*
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ProjectModel> getProjectById(@PathVariable("id") int id) {
		return new ResponseEntity<ProjectModel>(projectService.getProjectById(id),
				HttpStatus.OK);
	}*/
	
	
	
	
	/**
	 * get list of all projects
	 * @return ResponseEntity<List<ProjectModel>>
	 */
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/getProjects/{user_id}" , method = RequestMethod.GET)
//	@ResponseBody
//	public List getProjects(@PathVariable("user_id") int user_id) {
//		return projectService.getProjects(user_id);
//	}
	
}
