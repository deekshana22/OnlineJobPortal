package com.cg.edu.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cg.edu.dao.JobProviderRepository;
import com.cg.edu.dao.JobRepository;
import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.JobProviderNotFoundException;

@Service
public class JobServiceImpl implements JobService {
	
	private static final Logger logger;
	static {
		logger = LoggerFactory.getLogger(JobServiceImpl.class);
	}

	public static Logger getLogger() {
		return logger;
	}

	@Autowired
	JobRepository repo;
	
	@Autowired
	RestTemplate rest;
	
	
	/**
	 * This is the add job method which registers new job object.
	 * @param Job object
	 * @return Job object.
	 */
	@Override
	public Job addJob(Job job) {
		return repo.save(job);
	}

	/**
	 * This is get all user method which returns list of users who applied to a particular job.
	 * @param jobId
	 * @return List of user object
	 */
	@Override
	public List<User> getAllUser(Long jobId) {
		logger.info("-----------------------loading results -------------------------");
		ResponseEntity<List<JobApplication>> res =   rest.exchange("http://localhost:9595/ApplicationList",HttpMethod.GET, null, new ParameterizedTypeReference<List<JobApplication>>() {
			
		});
		List<JobApplication> list = res.getBody();
		
		List<User> userList = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			try {
				if(list.get(i).getJob().getJobId().equals(jobId)) {
					userList.add(list.get(i).getUser());
				}
			} catch(NullPointerException ex) {
				
			}
		}
		return userList;
	}

	/**
	 * This is find by city method which returns list of job objects.
	 * @param city
	 * @return List of job object.
	 */
	@Override
	public List<Job> findByCity(String city) {
		return repo.findByCity(city);
	}

	/**
	 * This is find by id method which returns job object with matching jobId.
	 * @param id
	 * @return Job object.
	 */
	@Override
	public Job findById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Job> findAllJobs() {
		return repo.findAll();
	}

	/**
	 * This is find all jobs by job provider id method which returns list of job objects.
	 * @return List of job object.
	 * @param jobProviderId.
	 */
	@Override
	public List<Job> findAllJobsByProviderId(JobProvider jobProvider) {
		return repo.findByProvider(jobProvider);
	}
	
}