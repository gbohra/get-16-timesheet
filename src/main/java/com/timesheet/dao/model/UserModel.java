package com.timesheet.dao.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="User")
public class UserModel {
	
		@Id
	    @Column(name="id")
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private int id;

	    public int getId() {
			return id;
		}
	    

		@Column(name="first_name")
	    private String firstName;

	    @Column(name="last_name")
	    private String lastName;
	    
	    @Column(name="email")
	    private String email;
	    
	    @Column(name="password")
	    private String password;
	    

		@Column(name="created_date")
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date createdDate;

		@Column(name="updated_date")
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date updatedDate;

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

}
