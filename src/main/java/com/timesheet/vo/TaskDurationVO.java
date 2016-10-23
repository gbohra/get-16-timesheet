package com.timesheet.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class TaskDurationVO {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	private int id;
	
	@NotNull
	private int taskId;
	
	private Date date;
	
	@NotNull
	private float duration;
	
}
