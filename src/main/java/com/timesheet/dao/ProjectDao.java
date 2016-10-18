package com.timesheet.dao;

//import all required classes
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
	 * create a new project
	 * 
	 * @param projectModel
	 * @return boolean : true if updated successfully else false
	 */
	public boolean createProject(ProjectModel projectModel) {
		// creating session object
		Session session = sessionFactory.getCurrentSession();
		session.save(projectModel);
		return true;
	}

	/**
	 * update project details
	 * 
	 * @param projectModel
	 * @return boolean : true if updated successfully else false
	 */
	public boolean updateProject(ProjectModel projectModel) {
		sessionFactory.getCurrentSession().update(projectModel);
		return true;
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
	 * @return List<ProjectModel> : List of project models
	 */
	@SuppressWarnings("unchecked")
	public List<ProjectModel> getAllProjects() {
		// creating session object
		Session session = sessionFactory.getCurrentSession();
		List<ProjectModel> criteria = session
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
	public ProjectModel getProjectById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (ProjectModel) session.get(ProjectModel.class, id);
	}
	/**
	 * This return project's list of a user
	 * @param id - user id;
	 * @return project's list
	 */
	@SuppressWarnings("rawtypes")
	public List getProjectByUserId(int id){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "FROM ProjectModel P WHERE createdBy = :id";
			Query query = session.createQuery(hql);
			List list = query.list();
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
}
