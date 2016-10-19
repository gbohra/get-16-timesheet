package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timesheet.dao.model.LabelModel;
import com.timesheet.service.LabelService;

/**
 * 
 * @author Avinash
 * This use for label operations
 */
//use for cross origin request
@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("api/v1/label")
public class LabelController {
	
	@Autowired
	private LabelService labelService;

	
	/**
	 * @return the labelservice
	 */
	public LabelService getLabelService() {
		return labelService;
	}

	/**
	 * @param labelservice the labelservice to set
	 */
	public void setLabelService(LabelService labelService) {
		this.labelService = labelService;
	}

	@RequestMapping(value = "/labels", method = RequestMethod.POST)
	public ResponseEntity<String> insertLabel(@RequestBody LabelModel label) {
		labelService.save(label);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/labels/{id}", method = RequestMethod.GET)
	public ResponseEntity<LabelModel> getLabelById(@PathVariable("id") long id) {
		return new ResponseEntity<LabelModel>(labelService.findById(id),
				HttpStatus.OK);
	}

	@RequestMapping(value="/labels" ,method = RequestMethod.GET)
	public ResponseEntity<List<LabelModel>> getLabelList() {
		return new ResponseEntity<List<LabelModel>>(labelService.findAll(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/labels/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updatelabel(@RequestBody LabelModel labelmodel,
			@PathVariable("id") long id) {
		labelService.save(labelmodel);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@RequestMapping(value = "/labels/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLabel(@PathVariable("id") long id) {
		labelService.deleteById(id);
    	return new ResponseEntity<String>(HttpStatus.OK);    	
    }
}
