package com.cg.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobProvider;
import com.cg.edu.dto.User;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	
	/**
	 * This is the findByCity method which finds all jobs by city.
	 * @param city
	 * @return list of job objects. 
	 */
	public List<Job> findByCity(String city);
	
	/**
	 * This is find jobs by job provider id method which returns list of job objects.
	 * @return List of job object.
	 * @param jobProviderId.
	 */
	public List<Job> findByProvider(JobProvider provider);
}
