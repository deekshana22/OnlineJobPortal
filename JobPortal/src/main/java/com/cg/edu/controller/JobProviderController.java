package com.cg.edu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.service.JobProviderService;
import com.cg.edu.service.JobService;

/**
 * @author Deekshana
 * controller class for jobprovider.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class JobProviderController {
	
	public static JobProvider currentProvider;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	JobProviderService providerService;
	
	/**
	 * Mapping for logging in as job provider.
	 */
	@GetMapping("/loginProvider/{jobProviderEmail}/{password}")
	public ResponseEntity<JobProvider> loginProvider(@PathVariable String jobProviderEmail, @PathVariable String password) {
		 JobProvider jobProvider = providerService.loginProvider(jobProviderEmail, password);
		 ResponseEntity<JobProvider> response;
		 if(jobProvider != null) {
			 currentProvider = jobProvider;
			 logger.info("-----------------------got current job provider session-------------------------");
			 response = new ResponseEntity<JobProvider>(currentProvider, HttpStatus.OK);
		 } else {
			 logger.error("-----------------------login failed-------------------------");
			 response = new ResponseEntity<JobProvider>(HttpStatus.NOT_FOUND);
		 }
		 
		return response;
	}
	
	/**
	 * Mapping for adding job by job provider.
	 */
	@PostMapping(path = "/addJob") 
	public ResponseEntity<Job> addJob(@RequestBody Job bean) {
		ResponseEntity<Job> response;
		try {
			Job job = jobService.addJob(bean);
			 if(job != null) {
				 logger.info("-----------------------new job posted with id: " + job.getJobId() + "-------------------------");
				response = new ResponseEntity<Job>(job, HttpStatus.OK);
			 } else {
				 logger.error("-----------------------could not create job post-------------------------");
				 response = new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
			 }
		} catch(Exception ex) {
			logger.error("-----------------------could not create job post-------------------------");
			response = new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
		 }
		return response;
	}
	
	/**
	 * Mapping for getting all the candidates applied to a particular job.
	 */
	@GetMapping(path = "/getAllCandidates/{jobId}")
	public  ResponseEntity<List<User>> getAllCandidates(@PathVariable Long jobId ) {
		List<User> userList = jobService.getAllUser(jobId);
		ResponseEntity<List<User>> response;
		 if(!userList.isEmpty()) {
			 logger.info("-----------------------results found-------------------------");
			 response = new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		 }else {
			 logger.error("-----------------------no results foundt-------------------------");
			 response = new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		 }
		return response;
	}
	
	/**
	 * Mapping for getting all the jobs added by a particular job provider.
	 */
	@GetMapping(path = "/provider/getAllJobs")
	public  ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobList = jobService.findAllJobsByProviderId(currentProvider);
		ResponseEntity<List<Job>> response;
		 if(!jobList.isEmpty()) {
			 logger.info("-----------------------results found-------------------------");
			 response = new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
		 }else {
			 logger.error("-----------------------no results foundt-------------------------");
			 response = new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		 }
		return response;
	}
	
	/**
	 * Logger for job provider controller class.
	 */
	private static final Logger logger;
	static {
		logger = LoggerFactory.getLogger(JobProviderController.class);
	}

	public static Logger getLogger() {
		return logger;
	}
}
