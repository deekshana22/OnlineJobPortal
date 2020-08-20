package com.cg.edu.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_provider_db")
public class JobProvider {
	
	@Id
	@GeneratedValue
	private Long providerId;
	private String jobProviderEmail;
	private String companyName;
	private String password;
	private String companyLogoUrl;
	
	public JobProvider() {
		// TODO Auto-generated constructor stub
	}

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
	
}
