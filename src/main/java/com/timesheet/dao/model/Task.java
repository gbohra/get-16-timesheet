package com.timesheet.dao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="sub_task")
	private String subTask;
	
	@Column(name="repeat_frequency")
	private int repeatFrequency;
	
	@Column(name="status")
	private int status;
	
	@Column(name="priority")
	private int priority;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "taskId", cascade = CascadeType.ALL)
	private List<TaskDurationModel> taskDurationModel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getSubTask() {
		return subTask;
	}

	public void setSubTask(String subTask) {
		this.subTask = subTask;
	}

	public int getRepeatFrequency() {
		return repeatFrequency;
	}

	public void setRepeatFrequency(int repeatFrequency) {
		this.repeatFrequency = repeatFrequency;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<TaskDurationModel> getTaskDurationModel() {
		return taskDurationModel;
	}

	public void setTaskDurationModel(List<TaskDurationModel> taskDurationModel) {
		this.taskDurationModel = taskDurationModel;
	}
	
	

}
