package com.timesheet.dao.model;

//import all required classes
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 * project model to describe the project table in data base
 * 
 * @author simran
 *
 */
@Entity
@Component
@Table(name = "project")
public class ProjectModel {

	// column mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	// column mapping
	@Column(name = "name")
	private String name;

	// column mapping
	@Column(name = "description")
	private String description;

	// column mapping
	@Column(name = "created_date")
	private Date createdDate;

	// column mapping
	@Column(name = "updated_date")
	private Date updatedDate;

	// column mapping
	@Column(name = "created_by")
	private int createdBy;

	// column mapping
	@Column(name = "updated_by")
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
