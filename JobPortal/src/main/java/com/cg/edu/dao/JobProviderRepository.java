package com.cg.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.edu.dto.Admin;
import com.cg.edu.dto.JobProvider;

@Repository
public interface JobProviderRepository extends JpaRepository<JobProvider, Long> {
	public JobProvider findByJobProviderEmail(String jobProviderEmail);
	public JobProvider findByProviderId(long providerId);
}
