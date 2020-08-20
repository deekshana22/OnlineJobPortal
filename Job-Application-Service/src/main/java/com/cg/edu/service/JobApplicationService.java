package com.cg.edu.service;

import java.util.List;

import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.User;

public interface JobApplicationService {
	
	/**
	 * This is the job apply method which creates one job application.
	 * @param jobApplication
	 * @return JobApplication object.
	 */
	public JobApplication apply(JobApplication jobApplication);
	
	public List<JobApplication> findAllJobApplication();
	
	public JobApplication findApplicationByJob(Job job, User user);
	
}