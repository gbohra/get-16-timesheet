package com.timesheet.dao;

//import all required classes
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.dao.model.ProjectModel;

/**
 * Database Access Layer for accessing Project
 * 
 * @author simran
 *
 */
@Repository
@Transactional
public class ProjectDao {

	// create bean of sessionFactory
	@Autowired
	private SessionFactory sessionFactory; // To create a session for the
											// database operation

	/**
	 * get sessionFactory
	 * 
	 * @return sessionFactory : session factory reference
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * set sessionFactory
	 * 
	 * @param sessionFactory
	 *            : session factory reference
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	 /**
     *create a new project
     * @param projectModel
     * @return ProjectModel : new created reference of ProjectModel
     */
	public ProjectModel createProject(ProjectModel projectModel) {
		// creating session object
		try{
			Session session = sessionFactory.getCurrentSession();
			session.save(projectModel);
			return projectModel;
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * update project details
     * @param projectModel
     * @return ProjectModel : new updated reference of ProjectModel
	 */
	public ProjectModel updateProject(ProjectModel projectModel) {
		try{
			sessionFactory.getCurrentSession().update(projectModel);
			return projectModel;
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * delete project by id
	 * 
	 * @param id
	 *            : id of the project to delete
	 * @return boolean : true if deleted successfully else false
	 */
	public boolean deleteProject(long id) {
		sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM ProjectModel WHERE id = " + id)
				.executeUpdate();
		return true;
	}

	/**
	 * get all projects
	 * 
	 * @return List : List of project models
	 */
	@SuppressWarnings({"rawtypes" })
	public List getAllProjects() {
		// creating session object
		Session session = sessionFactory.getCurrentSession();
		List criteria = session
				.createCriteria(ProjectModel.class).list();
		return criteria;
	}

	/**
	 * get project by id
	 * 
	 * @param id
	 *            : id of project to be fetched
	 * @return ProjectModel : project model having id equal to requested id
	 */
	public ProjectModel getProjectDetails(ProjectModel projectModel) {
		Session session = sessionFactory.getCurrentSession();
		return (ProjectModel) session.get(ProjectModel.class, projectModel.getId());
	}	
}
