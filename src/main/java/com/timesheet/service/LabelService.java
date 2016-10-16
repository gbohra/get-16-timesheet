package com.timesheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.timesheet.dao.LabelDAOImpl;
import com.timesheet.dao.model.LabelModel;

@Component
@Transactional
public class LabelService {

	@Autowired
	LabelDAOImpl labeldao;

	public LabelDAOImpl getLabeldao() {
		return labeldao;
	}

	public void setLabeldao(LabelDAOImpl labeldao) {
		this.labeldao = labeldao;
	}

	
	public void save(LabelModel labelVO) {
		if (labelVO.getId() == -1) {
			labeldao.insertLabel(labelVO);
		} else {
			labeldao.updateLabel(labelVO);
		}
	}

	/**
	 * return all data
	 * 
	 * @return
	 */
	public List<LabelModel> findAll() {
		return labeldao.getAllLabels();
	}

	/**
	 * find label by id
	 * 
	 * @param id
	 * @return
	 */
	public LabelModel findById(long id) {
		return labeldao.getLabelById(id);
	}

	public boolean deleteById(long id) {
		return labeldao.deleteLabelById(id);
	}

}
