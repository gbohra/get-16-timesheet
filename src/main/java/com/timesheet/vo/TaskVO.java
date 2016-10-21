package com.timesheet.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class TaskVO {
		
		
		private int id;
		
		@NotNull
		 @Size(min=10)
		private String description;
		
		
		private int createdBy;
		
		
		private Date createdDate;
		
		
		private String subTask;
		
		@NotNull
		private int repeatFrequency;
		
		@NotNull
		private int status;
		
		@NotNull
		private int priority;

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
		
		
}
