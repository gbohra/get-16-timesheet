package com.timesheet.controller;

//import all required classes
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.ProjectModel;
import com.timesheet.service.ProjectService;

/**
 * Controller for accessing ProjectService
 * 
 * @author simran
 *
 */
@Controller
@RequestMapping("/timesheet/projects")
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
	@RequestMapping(value="/",method = RequestMethod.POST)
	public ResponseEntity<String> createProject(@RequestBody ProjectModel projectModel) {
		projectService.createOrUpdateProject(projectModel);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * update project details
	 * @param projectModel
	 * @param id : id of project to be updated
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProject(@RequestBody ProjectModel projectModel,
			@PathVariable("id") long id) {
		projectService.createOrUpdateProject(projectModel);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * delete a project
	 * @param id : id of project to be deleted
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProject(@PathVariable("id") long id) {
		projectService.deleteProject(id);
    	return new ResponseEntity<String>(HttpStatus.OK);    	
    }
	
	/**
	 * get list of all projects
	 * @return ResponseEntity<List<ProjectModel>>
	 */
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ResponseEntity<List<ProjectModel>> getAllProjects() {
		return new ResponseEntity<List<ProjectModel>>(projectService.getAllProjects(),
				HttpStatus.OK);
	}
	
	/**
	 * get project details by id
	 * @param id : id of project to be retrieved
	 * @return ResponseEntity<ProjectModel>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ProjectModel> getProjectById(@PathVariable("id") long id) {
		return new ResponseEntity<ProjectModel>(projectService.getProjectById(id),
				HttpStatus.OK);
	}
}
