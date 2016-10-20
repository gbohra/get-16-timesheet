package com.timesheet.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;



public class OrganizationVO {
		
		private int id;

		@NotNull
		@Size(min=2, max=30)
		private String name;

		@NotBlank
		 @Size(min=10)
		private String description;

		@NotBlank
		 @Size(min=10)
		private String address;

		@NotBlank
		 @Email
		private String contact;

		
		private int createdBy;

		 
		private Date createdDate;

		
		private int updatedBy;

		
		private Date updatedDate;

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

		public String getAddress() {
			return address;
		}

		public void setAddress(String Address) {
			this.address = Address;
		}

		
		
		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
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

		public int getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(int updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
}
