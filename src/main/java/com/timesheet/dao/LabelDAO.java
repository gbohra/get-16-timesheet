package com.timesheet.dao;

import java.util.List;

import com.timesheet.dao.model.LabelModel;

public interface LabelDAO {
 public boolean insertLabel(LabelModel label);
 public List<LabelModel> getAllLabels();
 public LabelModel getLabelById(long id);
 public boolean deleteLabelById(long id);
 public boolean updateLabel(LabelModel labelvo);
}

