package com.timesheet.controller;

//import all required classes
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timesheet.dao.model.ProjectModel;
import com.timesheet.service.ProjectService;
import com.timesheet.vo.ProjectVO;

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
     * @return ProjectModel : new created reference of ProjectModel
     */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ProjectVO createProject(@RequestBody @Validated  ProjectVO projectVO , BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return projectVO;
		} else {
			return projectService.createProject(projectVO);
		}
	}
	
	/**
	 * update project details
     * @param projectModel
     * @return ProjectModel : new updated reference of ProjectModel
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ProjectVO updateProject(@RequestBody @Validated  ProjectVO projectVO , BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return projectVO;
		} else {
		return projectService.updateProject(projectVO);
		}
	}
	
	/**
	 * delete a project
	 * @param id : id of project to be deleted
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
		
    	return new ResponseEntity<String>(HttpStatus.OK);    	
    }
	
	/**
	 * get list of all projects in database
	 * @return ResponseEntity<List<ProjectModel>>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProjectModel>> getAllProjects() {
		return new ResponseEntity<List<ProjectModel>>(projectService.getAllProjects(),
				HttpStatus.OK);
	}

	/**
	 * get list of all projects
	 * @return ResponseEntity<List<ProjectModel>>
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProjects/{user_id}" , method = RequestMethod.GET)
	@ResponseBody
	public List getProjects(@PathVariable("user_id") int user_id) {
		return projectService.getProjects(user_id);
	}
	
	/**
	 * get project details by id
	 * @param id : id of project to be retrieved
	 * @return ResponseEntity<ProjectModel>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectModel getProjectDetails(@PathVariable("id") int id) {
		return projectService.getProjectDetails(id);
	}
		
}
