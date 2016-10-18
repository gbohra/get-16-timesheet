package com.timesheet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.timesheet.dao.model.Organization;

@Repository
@Transactional
public class OrganizationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @param organization -- organization object
	 * @return true if successfully added else return false
	 */
	public boolean addOrganization(Organization organization){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.save(organization);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getUserOrganization(int id){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "From Organization o WHERE  o.createdBy = :user_id";
			Query query = session.createQuery(hql);
			query.setParameter("user_id", id);
			List list = query.list();
			return list;
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * 
	 * @param id
	 */
	
	public void getOrganizationProject(int id){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "SELECT `Project` FROM project p where p.id IN (select OrganizationProject.projectId from OrganizationProject where OrganizationProject.orgId=:orgId)";
			Query query = session.createQuery(hql);
			query.setParameter("orgId", id);
			//List<Project> list = query.list();
		}
		catch(Exception e){
			
		}
	}

}
