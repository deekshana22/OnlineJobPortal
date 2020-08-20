package com.cg.edu.service;

import java.util.List;

import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.JobProviderNotFoundException;

public interface JobProviderService {

	/**
	 * This is the add job provider method which registers new job provider.
	 * @param JobProvider
	 * @return JobProvider object.
	 */
	public JobProvider addJobProvider(JobProvider provider);
	
	/**
	 * This is the job provider by Id method which returns job provider.
	 * @param id
	 * @return JobProvider object.
	 */
	public JobProvider getProviderById(Long id);
	
	/**
	 * This is the get all job provider method which returns job provider.
	 * @return JobProvider object.
	 */
	public List<JobProvider> getAllProviders();
	
	/**
	 * This is the login method for job provider.
	 * @param id.
	 * @param password.
	 * @return string message.
	 */
	public JobProvider loginProvider(String jobProviderEmail, String password);

	public List<JobProvider> deleteProvider(Long providerId) throws JobProviderNotFoundException;

	public JobProvider updateProvider(JobProvider bean);
	
}
