package com.timesheet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public Organization addOrganization(Organization organization){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.save(organization);
			return organization;
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * 
	 * @param 
	 * @return List of organization
	 */
	@SuppressWarnings("rawtypes")
	public List getOrganizations(){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "From Organization";
			Query query = session.createQuery(hql);
//			query.setParameter("user_id", id);
			System.out.println(query.list());
			List list = query.list() ;
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param organization to update
	 * @return organization
	 */
	public Organization updateOrganization(Organization organization){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			session.update(organization);			
			return organization;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param id - user's id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Organization getOrganization(int id){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "From Organization o WHERE  o.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			System.out.println(query.list());
			List list = query.list() ;
			
			return (Organization) list.get(0);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param id - organization's id
	 */
	
	@SuppressWarnings({ "rawtypes" })
	public List getOrganizationProject(int id){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "FROM project p where p.id IN (select OrganizationProject.projectId from OrganizationProject where OrganizationProject.orgId=:orgId)";
			Query query = session.createQuery(hql);
			query.setParameter("orgId", id);
			List list = query.list();
			return list;
		}
		catch(Exception e){
			return null;
		}
	}

}
