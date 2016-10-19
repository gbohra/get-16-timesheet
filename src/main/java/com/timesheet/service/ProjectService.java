package com.timesheet.service;

//import all required classes
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.ProjectDao;
import com.timesheet.dao.model.ProjectModel;

/**
 * Service Layer for accessing ProjectDAO
 * 
 * @author simran
 *
 */
@Service
public class ProjectService {

	// create bean of ProjectDAO
	@Autowired
	private ProjectDao projectDao;

	/**
	 * get projectDAO
	 * 
	 * @return projectDAO : projectDAO reference
	 */
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	/**
	 * set projectDAO
	 * 
	 * @param projectDAO
	 *            : projectDAO reference
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * method to save the project details. Either create a new entry or update
	 * existing depending upon the existing entries in the database
	 * 
	 * @param projectModel
	 */
	public void createOrUpdateProject(ProjectModel projectModel) {
		// create a new project if the requested id does not exist
		if (projectModel.getId() == -1) {
			projectDao.createProject(projectModel);
		}
		// update the project details if the requested id does exists
		else {
			projectDao.updateProject(projectModel);
		}
	}

	/**
	 * delete a project
	 * 
	 * @param id
	 *            : id of project to be deleted
	 * @return boolean : true if updated successfully else false
	 */
	public boolean deleteProject(long id) {
		return projectDao.deleteProject(id);
	}

	/**
	 * get all projects
	 * 
	 * @return List<ProjectModel> : List of project models
	 */
	@SuppressWarnings("rawtypes")
	public List getAllProjects() {
		return projectDao.getAllProjects();
	}

	/**
	 * get project details by id
	 * 
	 * @param id
	 *            : id of project to be retrieved
	 * @return ProjectModel : project model having id equal to requested id
	 */
	public ProjectModel getProjectById(long id) {
		return projectDao.getProjectById(id);
	}
}
