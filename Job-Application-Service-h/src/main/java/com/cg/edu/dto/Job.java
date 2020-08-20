package com.cg.edu.dto;

import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

//import com.cg.edu.controller.JobProviderController;

/**
 * @author HarshitSharma
 * all the jobs created by job provider will be saved inside this entity.
 */
@Entity
@Table(name="job_db")
public class Job {

	@Id
	@GeneratedValue
	private Long jobId;
	private String field;
	private String title;
	private String city;
	private Double salary;
	private Double experience;
	private String jobType;
	
	@OneToOne
	@JoinColumn(name="providerId")
	private JobProvider provider ; 

	public Job() {
		// TODO Auto-generated constructor stub
	}

	

	public Long getJobId() {
		return jobId;
	}



	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}



	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public JobProvider getProvider() {
		return provider;
	}

	public void setProvider(JobProvider provider) {
		this.provider = provider;
	}



	public Double getExperience() {
		return experience;
	}



	public void setExperience(Double experience) {
		this.experience = experience;
	}



	public String getJobType() {
		return jobType;
	}



	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
}
