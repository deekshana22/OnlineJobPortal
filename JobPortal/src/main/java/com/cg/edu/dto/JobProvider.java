package com.cg.edu.dto;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="job_provider_db")
public class JobProvider {
	
	@Id
	@GeneratedValue
	private Long providerId;
	@NotNull
	private String jobProviderEmail;
	private String companyName;
	private String password;
	private String companyLogoUrl;
//	@ElementCollection
//	private List<Job> jobs;
//	
//	
//	public List<Job> getJobs() {
//		return jobs;
//	}
//
//	public void setJobs(List<Job> jobs) {
//		this.jobs = jobs;
//	}

	public JobProvider() {
		// TODO Auto-generated constructor stub
	}

//	public JobProvider(Long providerId, String jobProviderEmail, String companyName, String password,
//			String companyLogoUrl, List<Job> jobs) {
//		super();
//		this.providerId = providerId;
//		this.jobProviderEmail = jobProviderEmail;
//		this.companyName = companyName;
//		this.password = password;
//		this.companyLogoUrl = companyLogoUrl;
//		this.jobs = jobs;
//	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getJobProviderEmail() {
		return jobProviderEmail;
	}

	public void setJobProviderEmail(String jobProviderEmail) {
		this.jobProviderEmail = jobProviderEmail;
	}

	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}

	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}

	@Override
	public String toString() {
		return "JobProvider [providerId=" + providerId + ", jobProviderEmail=" + jobProviderEmail + ", companyName="
				+ companyName + ", password=" + password + ", companyLogoUrl=" + companyLogoUrl + "]";
	}
	
	

	}	
	

