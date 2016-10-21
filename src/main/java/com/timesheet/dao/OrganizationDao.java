package com.timesheet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.dao.model.OrganizationModel;

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
	public OrganizationModel addOrganization(OrganizationModel organization){
		System.out.println(" i am in dao");
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
			String hql = "From OrganizationModel";
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
	public OrganizationModel updateOrganization(OrganizationModel organization){
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
	public OrganizationModel getOrganization(OrganizationModel organizationModel){
		Session session = this.sessionFactory.getCurrentSession();
		try{
			String hql = "From OrganizationModel o WHERE  o.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", organizationModel.getId());
			System.out.println(query.list());
			@SuppressWarnings("unchecked")
			List<OrganizationModel> list = query.list() ;
			
			return (OrganizationModel) list.get(0);
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
