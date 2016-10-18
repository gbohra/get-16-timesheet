/*package com.timesheet.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "label")
public class LabelVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long Updated_by;
	private long Created_by;

	private Date Created_date;
	private Date Updated_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUpdated_by() {
		return Updated_by;
	}

	public void setUpdated_by(long updated_by) {
		Updated_by = updated_by;
	}

	public long getCreated_by() {
		return Created_by;
	}

	public void setCreated_by(long created_by) {
		Created_by = created_by;
	}

	public Date getCreated_date() {
		return Created_date;
	}

	public void setCreated_date(Date created_date) {
		Created_date = created_date;
	}

	public Date getUpdated_date() {
		return Updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		Updated_date = updated_date;
	}

}
*/