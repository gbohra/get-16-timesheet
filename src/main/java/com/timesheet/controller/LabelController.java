package com.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timesheet.dao.model.LabelModel;
import com.timesheet.service.LabelService;

@Controller
@RequestMapping("/timesheet")
public class LabelController {
	@Autowired
	LabelService labelservice;

	
	@RequestMapping(value = "/labels", method = RequestMethod.POST)
	public ResponseEntity<String> insertLabel(@RequestBody LabelModel label) {
	labelservice.save(label);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/labels/{id}", method = RequestMethod.GET)
	public ResponseEntity<LabelModel> getLabelById(@PathVariable("id") long id) {
		return new ResponseEntity<LabelModel>(labelservice.findById(id),
				HttpStatus.OK);
	}

	@RequestMapping(value="/labels" ,method = RequestMethod.GET)
	public ResponseEntity<List<LabelModel>> getLabelList() {
		return new ResponseEntity<List<LabelModel>>(labelservice.findAll(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/labels/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updatelabel(@RequestBody LabelModel labelmodel,
			@PathVariable("id") long id) {
		labelservice.save(labelmodel);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@RequestMapping(value = "/labels/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLabel(@PathVariable("id") long id) {
		labelservice.deleteById(id);
    	return new ResponseEntity<String>(HttpStatus.OK);    	
    }
}
