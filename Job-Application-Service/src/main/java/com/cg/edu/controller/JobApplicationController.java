package com.cg.edu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.User;
import com.cg.edu.service.JobApplicationService;
import com.cg.edu.service.JobApplicationServiceImpl;

@RestController
public class JobApplicationController {
	
	public static User currentUser;
	public static Job currentJob;
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	JobApplicationService service;
	
	private static Logger myLogger;
	/**
	 * static block to declare logger and create the instance of DaoImpl
	 */

	static {
		setMyLogger(LoggerFactory.getLogger(JobApplicationController.class));
	}
	/**
	 * @return the myLogger
	 */
	public static Logger getMyLogger() {
		return myLogger;
	}

	/**
	 * @param myLogger the myLogger to set
	 */
	public static void setMyLogger(Logger myLogger) {
		JobApplicationController.myLogger = myLogger;
	}
	
	
	
	@GetMapping(path = "/ApplicationList") 
	public  List<JobApplication> getAllCandidates() {
		List<JobApplication> list = service.findAllJobApplication();
		return list;
	}
	
	@PostMapping(path = "/findApplicationByJobAndUser") 
	public  JobApplication findApplicationByJob(@RequestBody JobApplication jobApplication) {
		myLogger.info("-----------------Post request-----------------");
		JobApplication responseJobApplication = service.findApplicationByJob(jobApplication.getJob(), jobApplication.getUser());
		return jobApplication;
	}
	
	@PostMapping(path = "/applyForJob") 
	public  ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplication application) {
		ResponseEntity<JobApplication> response;
		JobApplication jobApplication = service.apply(application);
		if(jobApplication != null) {
			response = new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
		} else {
			response = new ResponseEntity<JobApplication>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
}
