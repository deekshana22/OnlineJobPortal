package com.cg.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.edu.dto.Job;
import com.cg.edu.dto.JobApplication;
import com.cg.edu.dto.User;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
	public List<JobApplication> findByJob(Job job);
}
