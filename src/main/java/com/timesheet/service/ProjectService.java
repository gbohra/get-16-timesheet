package com.timesheet.service;

//import all required classes
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.ProjectDao;
import com.timesheet.dao.model.ProjectModel;
import com.timesheet.vo.ProjectVO;

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
	
	protected ProjectModel populateVOIntoModel(ProjectVO projectVO, ProjectModel projectModel) {
        BeanUtils.copyProperties(projectVO, projectModel);
        return projectModel;
    }

    protected ProjectVO populateModelIntoVO(ProjectVO projectVO, ProjectModel projectModel) {
        BeanUtils.copyProperties(projectModel , projectVO);
        return projectVO;
    }

	 /**
     *create a new project
     * @param projectModel
     * @return ProjectModel : new created reference of ProjectModel
     */
	public ProjectVO createProject(ProjectVO projectVO) {
		ProjectModel projectModel =  new ProjectModel();
		projectModel = populateVOIntoModel(projectVO, projectModel);
		projectModel = projectDao.createProject(projectModel);
		return populateModelIntoVO(projectVO, projectModel);
	}
	
	/**
	 * update project details
     * @param projectModel
     * @return ProjectModel : new updated reference of ProjectModel
	 */
	public ProjectVO updateProject(ProjectVO projectVO) {
		ProjectModel projectModel =  new ProjectModel();
		projectModel = populateVOIntoModel(projectVO, projectModel);
		projectModel = projectDao.updateProject(projectModel);
		return populateModelIntoVO(projectVO, projectModel);
	}

	/**
	 * delete a project
	 * 
	 * @param id
	 *            : id of project to be deleted
	 * @return boolean : true if updated successfully else false
	 */
	public boolean deleteProject(int id) {
		return projectDao.deleteProject(id);
	}

	/**
	 * get all projects
	 * 
	 * @return List : List of project models
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
	public ProjectVO getProjectDetails(ProjectVO projectVO) {
		ProjectModel projectModel =  new ProjectModel();
		projectModel = populateVOIntoModel(projectVO, projectModel);
		projectModel = projectDao.getProjectDetails(projectModel);
		return populateModelIntoVO(projectVO, projectModel);
	}
	
}