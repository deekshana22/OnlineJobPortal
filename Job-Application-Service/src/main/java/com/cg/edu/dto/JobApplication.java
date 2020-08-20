package com.cg.edu.dto;

import javax.persistence.*;

import com.cg.edu.controller.JobApplicationController;

@Entity
@Table(name="job_application_db")
public class JobApplication {

	@Id
	@GeneratedValue
	private Long applicationId;
	
	@OneToOne
	@JoinColumn(name="jobId")
	private Job job;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	

	public JobApplication() {
		// TODO Auto-generated constructor stub
	}

	public JobApplication(Long applicationId, Job job, User user) {
		this.applicationId = applicationId;
		this.job = job;
		this.user = user;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
