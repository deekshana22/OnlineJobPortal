package com.cg.edu.service;

import java.util.List;

import com.cg.edu.dto.*;
import com.cg.edu.exceptions.JobProviderNotFoundException;

public interface JobService {
	
	/**
	 * This is the add job method which registers new job object.
	 * @param Job object
	 * @return Job object.
	 * @throws JobProviderNotFoundException 
	 */
	public Job addJob(Job job) ;
	
	/**
	 * This is get all user method which returns list of users who applied to a particular job.
	 * @param jobId
	 * @return List of user object.
	 */
	public List<User> getAllUser(Long jobId);
	
	/**
	 * This is find by city method which returns list of job objects.
	 * @param city
	 * @return List of job object.
	 */
	public List<Job> findByCity(String city);
	
	/**
	 * This is find all jobs method which returns list of job objects.
	 * @return List of job object.
	 */
	public List<Job> findAllJobs();
	
	/**
	 * This is find all jobs by job provider id method which returns list of job objects.
	 * @return List of job object.
	 * @param jobProviderId.
	 */
	public List<Job> findAllJobsByProviderId(JobProvider jobProviderId);
	
	/**
	 * This is find by id method which returns job object with matching jobId.
	 * @param id
	 * @return Job object.
	 */
	public Job findById(Long id);


}
