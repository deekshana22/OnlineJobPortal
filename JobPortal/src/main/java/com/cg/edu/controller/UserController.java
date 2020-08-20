package com.cg.edu.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;
import com.cg.edu.exceptions.UserNotFoundException;
//import com.cg.edu.service.JobApplicationService;
import com.cg.edu.service.JobService;
import com.cg.edu.service.UserService;

/**
 * @author Nikshitha
 * controller class for users.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	JobService jobService;
	
	public static User currentUser ;
	public static Job currentJob ;
	
	/**
	 * create logger object.
	 */
	private static final Logger logger;
	static {
		logger = LoggerFactory.getLogger(UserController.class);
	}

	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Mapping for logging in as user.
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/loginUser/{userEmail}/{password}")
	public ResponseEntity<User> loginUser(@PathVariable String userEmail, @PathVariable String password) {
		 User user =userService.loginUser(userEmail, password);
		 ResponseEntity<User> response;
		 if(user != null) {
			 currentUser = user;
			 logger.info("-----------------------got current user session-------------------------");
			 response = new ResponseEntity<User>(currentUser, HttpStatus.OK);
		 } else {
			 logger.error("-----------------------login failed-------------------------");
			 response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		 }
		 
		return response;
	}
		
	/**
	 * Mapping for applying for job by user.
	 */
	@PostMapping(path = "/applyForJob")
	public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplication job) {
		ResponseEntity<JobApplication> response;
		logger.info("-----------------------trying to apply-------------------------");
		JobApplication jobApplication = restTemplate.postForObject("http://localhost:9595/findApplicationByJobAndUser", job, JobApplication.class);
		
		response = new  ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
		
		return response;
	}
	
	/**
	 * Mapping to search all jobs.
	 */
	@GetMapping(path = "/getAllJob")
	public  ResponseEntity<List<Job>> getAllJob() {
			ResponseEntity<List<Job>> response;
			List<Job> jobList = jobService.findAllJobs();
			 if(!jobList.isEmpty()) {
				 logger.info("-----------------------found all jobs-------------------------");
				 response = new  ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
			 }
			 else {
				 logger.error("-----------------------no jobs found-------------------------");
				 response = new  ResponseEntity<List<Job>>(HttpStatus.NOT_FOUND);
			}
		return response;
	}
	
	/**
	 * Mapping to search jobs by city.
	 */
	@GetMapping(path = "/getJobByCity/{city}")
	public  ResponseEntity<List<Job>> getJobByCity(@PathVariable String city) {
			ResponseEntity<List<Job>> response;
			List<Job> jobList = jobService.findByCity(city);
			 if(!jobList.isEmpty()) {
				 logger.info("-----------------------found jobs by city-------------------------");
				 response = new  ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
			 }
			 else {
				 logger.error("-----------------------no jobs found by city-------------------------");
				 response = new  ResponseEntity<List<Job>>(HttpStatus.NOT_FOUND);
			}
		return response;
	}
	
	/**
	 * Mapping to find job by id.
	 */
	
	@GetMapping(path = "/getJobById/{id}")
	public  ResponseEntity<Job> getJobByCity(@PathVariable Long id) {
		ResponseEntity<Job> response;
		try {
			Job job = jobService.findById(id);
			 if(job != null) {
				 logger.info("-----------------------found one job with id: " + id + "-------------------------");
				 response = new  ResponseEntity<Job>(job, HttpStatus.OK);
				 currentJob = jobService.findById(id);
			 } else {
				 logger.error("-----------------------no job found with id: " + id + "-------------------------");
				 response = new  ResponseEntity<Job>(HttpStatus.NOT_FOUND);
			 }
		 }catch(Exception ex) {
			 logger.error("-----------------------no job found with id: " + id + "-------------------------");
			 response = new  ResponseEntity<Job>(HttpStatus.NOT_FOUND);
		 }
		return response;
	}
	
	
	
}
