package com.timesheet.controller;

//import all required classes
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
import com.timesheet.utill.BasicAuthorization;
import com.timesheet.vo.ProjectVO;

/**
 * Controller for accessing ProjectService
 * 
 * @author simran
 *
 */
//@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
@RequestMapping("/api/v1/projects")
public class ProjectController {

	// create bean of ProjectService
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectVO projectVO;
	
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
	
	public ProjectVO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}


    /**
     *create a new project
     * @param projectModel
     * @return ResponseEntity<String> : String
     */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProjectVO> create(@RequestBody @Validated  ProjectVO projectVO , BindingResult bindingResult, ServletRequest request) {
		if(bindingResult.hasErrors()) {
//			System.out.println(bindingResult.getAllErrors().get(0));
			return new ResponseEntity<ProjectVO>(projectVO, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			return new ResponseEntity<ProjectVO>(projectVO, HttpStatus.OK);
		}
	}
	/**
	 * update project details
	 * @param projectModel
	 * @param id : id of project to be updated
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ProjectVO> updateProject(@RequestBody @Validated  ProjectVO projectVOTemp , BindingResult bindingResult, ServletRequest request) {
//		System.out.println("ProjectVOTEMP   : "+projectVOTemp.getName());
		// Gadbad Line
		projectVO = projectService.getProjectDetails(projectVOTemp);
		// Gadbad Line
//		System.out.println("3457834985743985745957349857");
//		System.out.println("ProjectVO   :  " + projectVO.getName());
//		System.out.println("ProjectVOTEMP   : "+projectVOTemp.getName());
		if (BasicAuthorization.isProjectUpdateAllowed(projectVO, request)){
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<ProjectVO>(projectVOTemp, HttpStatus.UNPROCESSABLE_ENTITY);
			}else{
				// update project
				projectVO = projectService.updateProject(projectVOTemp);
				return new ResponseEntity<ProjectVO>(projectVO, HttpStatus.OK);
			}
		}else{
			return new ResponseEntity<ProjectVO>(projectVO, HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * delete a project
	 * @param id : id of project to be deleted
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProject(@PathVariable("id") int id, ServletRequest request) {
		projectVO.setId(id);
		projectVO = projectService.getProjectDetails(projectVO);
		if(BasicAuthorization.isProjectDeleteAllowed(projectVO, request)){
			projectService.deleteProject(projectVO.getId());
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
	public ProjectVO getProjectDetails(@PathVariable("id") int id, ServletRequest request) {
		projectVO.setId(id);
		projectVO = projectService.getProjectDetails(projectVO);
		if(BasicAuthorization.isProjectReadAllowed(projectVO, request)){
			return projectVO;
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
	public ResponseEntity<List<ProjectModel>> getMyProjects() {
		return new ResponseEntity<List<ProjectModel>>(projectService.getMyProjects(),
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