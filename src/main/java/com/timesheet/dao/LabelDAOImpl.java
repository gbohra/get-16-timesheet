package com.timesheet.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.timesheet.dao.model.LabelModel;

@Component
public class LabelDAOImpl  {
	// to create bean of sessionFactory
	@Autowired            
	SessionFactory sessionFactory; // To create a session for the database operation
     
	public boolean insertLabel(LabelModel label) {
		// creating session object
		Session session = sessionFactory.getCurrentSession();
		session.save(label);
		return true;

	}
	
    /*
     * to get list of all labels
     * having their id,name,updated_id,updated_date,created_by,created_date
     */
	public List<LabelModel> getAllLabels() {
		//creating session object  
	    Session session=sessionFactory.getCurrentSession();  
		List<LabelModel> criteria = session.createCriteria(LabelModel.class).list();
		System.out.println(criteria);
	return criteria;	
	}
	
	/*
	 * to get all details of label having id 
	 * given in path variable
	 */
	public LabelModel getLabelById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (LabelModel) session.get(LabelModel.class, id);
	}

	
	/*
	 * to delete all details of label having id 
	 * given in path variable
	 */
	public boolean deleteLabelById(long id) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM LabelModel WHERE id = "+id).executeUpdate();
		return true;
	}

	/*
	 * to update details of label having id 
	 * given in path variable
	 */
	public boolean updateLabel(LabelModel labelvo) {
		sessionFactory.getCurrentSession().update(labelvo);
	return true;
	}

}
