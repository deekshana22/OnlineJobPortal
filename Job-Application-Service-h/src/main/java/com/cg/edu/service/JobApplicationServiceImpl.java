package com.cg.edu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.edu.dao.JobApplicationRepository;
import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.User;


/**
* @author HarshitSharma
* 
*/

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
	
	private static Logger myLogger;
	/**
	 * static block to declare logger
	 */

	static {
		setMyLogger(LoggerFactory.getLogger(JobApplicationServiceImpl.class));
	}
	
	
	@Autowired
	JobApplicationRepository repo;

	
	
	/**
	 * This is the job apply method which creates one job application.
	 * @param jobApplication
	 * @return JobApplication object.
	 */
	
	@Override
	public JobApplication apply(JobApplication application) {
		return repo.save(application);
	}



	@Override
	public List<JobApplication> findAllJobApplication() {
		List<JobApplication> list = new ArrayList<>();
		Iterable<JobApplication> iterable = repo.findAll();
		Iterator<JobApplication> iterator = iterable.iterator();

		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}
	
	@Override
	public JobApplication findApplicationByJob(Job job, User user) {
		List<JobApplication> jobApplicationList = repo.findByJob(job);
		JobApplication app = null;
		for(int i=0; i<jobApplicationList.size(); i++) {
			try {
				if(jobApplicationList.get(i).getUser().getUserId().equals(user.getUserId())) {
					myLogger.info("-----------------application found-----------------");
					app = jobApplicationList.get(i);
					break;
				} 
			} catch(NullPointerException ex) {
				
			}
		}
		
		try {
			if(app.getApplicationId() != null) {
				myLogger.info("-----------------already applied to job-----------------");
			} else {
				app = new JobApplication();
				app.setJob(job);
				app.setUser(user);
				this.apply(app);
				myLogger.info("-----------------fresh job application-----------------");
			}
		}catch(NullPointerException ex) {
			app = new JobApplication();
			myLogger.info("-----------------fresh job application-----------------");
		}
		return app;
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
		JobApplicationServiceImpl.myLogger = myLogger;
	}



	
	
}
