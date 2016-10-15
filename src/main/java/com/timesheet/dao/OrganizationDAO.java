package com.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.dao.model.OrganizationModel;

/**
 * 
 * @author Kritik
 *
 */
@Repository(value = "OrganizationDAO")
@Transactional
public class OrganizationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 
	 * @param organizationModel
	 * @return
	 */
	public boolean inset(OrganizationModel organizationModel) {

		try {
			// creating a session
			Session session = this.sessionFactory.getCurrentSession();
			session.save(organizationModel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param organizationModel
	 * @return
	 */
	public boolean update(OrganizationModel organizationModel) {

		try {
			// creating a session
			Session session = this.sessionFactory.getCurrentSession();
			session.update(organizationModel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * 
	 * @param organizationModel
	 * @return
	 */
	public boolean delete(OrganizationModel organizationModel) {

		try {
			// creating a session
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(organizationModel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
