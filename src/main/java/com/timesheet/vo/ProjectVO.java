package com.timesheet.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * VO for project
 * 
 * @author simran
 *
 */
@Component
@Scope("prototype")
public class ProjectVO {

	private int id;
	
	@NotNull
	
	@Size(min=2, max=30)
	private String name;
	
	@NotNull
	 @Size(min=2)
	private String description;
	 
	private Date createdDate;
	private Date updatedDate;
	private int createdBy;
	private int updatedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
}
