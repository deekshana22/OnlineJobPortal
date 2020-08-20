package com.cg.edu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.edu.controller.JobProviderController;
import com.cg.edu.dao.JobProviderRepository;
import com.cg.edu.dto.Admin;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.AdminNotFoundException;
import com.cg.edu.exceptions.JobNotFoundException;
import com.cg.edu.exceptions.JobProviderNotFoundException;
import com.cg.edu.exceptions.UserNotFoundException;
import com.cg.edu.util.ErrorMessageUtil;

@Service
@Transactional
public class JobProviderServiceImpl implements JobProviderService{
	
	private static final Logger logger;
	static {
		logger = LoggerFactory.getLogger(JobProviderServiceImpl.class);
	}

	public static Logger getLogger() {
		return logger;
	}
	
	@Autowired
	JobProviderRepository jobProviderRepository;

	/**
	 * This is the login method for job provider.
	 * @param email.
	 * @param password.
	 * @return string message.
	 */
	@Override
	public JobProvider loginProvider(String jobProviderEmail, String password) {
		JobProvider provider = jobProviderRepository.findByJobProviderEmail(jobProviderEmail);
		
		if(provider.getPassword().equals(password.toString())) {
			
		} else {
			provider = null;
		}
		return provider;
	}
	
	/**
	 * This is the add job provider method which registers new job provider.
	 * @param JobProvider
	 * @return JobProvider object.
	 */
	@Override
	public JobProvider addJobProvider(JobProvider provider) {
		return jobProviderRepository.save(provider);
	}
	
	/**
	 * This is the update job provider method which updates job provider details.
	 * @param JobProvider
	 * @return JobProvider object.
	 */
	@Override
	public JobProvider updateProvider(JobProvider bean) {
		return jobProviderRepository.save(bean);
	}


	/**
	 * This is the job provider by Id method which returns job provider.
	 * @param id
	 * @return JobProvider object.
	 */
	@Override
	public JobProvider getProviderById(Long id) {
		JobProvider jobProvider = jobProviderRepository.findById(id).get();
		try {
			if(!jobProvider.getProviderId().equals(id)) {
				throw new JobNotFoundException(ErrorMessageUtil.FIND_JOB_PROVIDER_ERROR);
			}
		} catch (JobNotFoundException e) {
			jobProvider = null;
		}
		
		return jobProvider;
	}


	/**
	 * This is the get all job provider method which returns job provider.
	 * @return JobProvider object.
	 */
	@Override
	public List<JobProvider> getAllProviders() {
		return (List<JobProvider>) jobProviderRepository.findAll();
	}

	/**
	 * This is the delete job provider method.
	 */
	@Override
	public List<JobProvider> deleteProvider(Long providerId) throws JobProviderNotFoundException {
		JobProvider provider = jobProviderRepository.findByProviderId(providerId);
		if(provider.getProviderId() != providerId) {
			throw new JobProviderNotFoundException(ErrorMessageUtil.FIND_JOB_PROVIDER_ERROR);
		}
		jobProviderRepository.delete(provider);
		return getAllProviders();
	
	}
}

