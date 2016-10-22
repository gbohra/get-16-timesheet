package com.timesheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.dao.LabelDao;
import com.timesheet.dao.model.LabelModel;

@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;

	
	/**
	 * @return the labelDao
	 */
	public LabelDao getLabelDao() {
		return labelDao;
	}

	/**
	 * @param labelDao the labelDao to set
	 */
	public void setLabelDao(LabelDao labelDao) {
		this.labelDao = labelDao;
	}

	public void save(LabelModel labelVO) {
		if (labelVO.getId() == -1) {
			labelDao.insertLabel(labelVO);
		} else {
			labelDao.updateLabel(labelVO);
		}
	}

	/**
	 * return all data
	 * 
	 * @return
	 */
	public List<LabelModel> findAll() {
		return labelDao.getAllLabels();
	}

	/**
	 * find label by id
	 * 
	 * @param id
	 * @return
	 */
	public LabelModel findById(long id) {
		return labelDao.getLabelById(id);
	}

	public boolean deleteById(long id) {
		return labelDao.deleteLabelById(id);
	}

}
